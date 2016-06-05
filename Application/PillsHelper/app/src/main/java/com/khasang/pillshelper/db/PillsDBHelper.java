package com.khasang.pillshelper.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.Drug;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class PillsDBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pills.db";
    private static final int DATABASE_VERSION = 1;
    private static volatile PillsDBHelper instance;

    public static void init(Context context){
        if(instance == null){
            instance = new PillsDBHelper(context.getApplicationContext());
            instance.getReadableDatabase();
        }
    }

    public static PillsDBHelper getInstance(){
        if(instance == null){
            throw new IllegalStateException("database is not initialized");
        }
        return instance;
    }

    private PillsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //TODO method for test, it will be deleted
    public void fillDBTest(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("course", null, null);
        db.delete("taking_time", null, null);
        //every day at 9.00, 16.00 and 23.00, start - now, end - undefined
        List<LocalTime> takingTime0 = new ArrayList<>();
        takingTime0.add(new LocalTime(9, 0));
        takingTime0.add(new LocalTime(16, 0));
        takingTime0.add(new LocalTime(23, 0));
        Instant startDate0 = Instant.now();
        addCourse(new Drug(64), startDate0, null, takingTime0, 1);

        //every 5th day at 19.00, start - now, end - undefined
        List<LocalTime> takingTime1 = new ArrayList<>();
        takingTime1.add(new LocalTime(19, 0));
        Instant startDate1 = Instant.now();
        addCourse(new Drug(128), startDate1, null, takingTime1, 5);

        //every 2th day at 8.00 and 18.00, start - now + 2 days, end - undefined
        List<LocalTime> takingTime2 = new ArrayList<>();
        takingTime2.add(new LocalTime(8, 0));
        takingTime2.add(new LocalTime(18, 0));
        Instant startDate2 = Instant.now().plus(Duration.standardDays(2));
        addCourse(new Drug(256), startDate2, null, takingTime2, 2);

        //every day at 8.00, 9.00, 10.00, 11.00, 12.00, 13.00, start - now, end - now + 15 days
        List<LocalTime> takingTime3 = new ArrayList<>();
        takingTime3.add(new LocalTime(8, 0));
        takingTime3.add(new LocalTime(9, 0));
        takingTime3.add(new LocalTime(10, 0));
        takingTime3.add(new LocalTime(11, 0));
        takingTime3.add(new LocalTime(12, 0));
        takingTime3.add(new LocalTime(13, 0));
        Instant startDate3 = Instant.now();
        Instant endDate3 = Instant.now().plus(Duration.standardDays(15));
        addCourse(new Drug(512), startDate3, endDate3, takingTime3, 1);
    }

    /**
     * Get list of all courses which stored in database
     * @return list of courses
     */
    public List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor courseCursor = db.rawQuery("select _id, drug_id, start, end, interval from course c", null);

        if(courseCursor != null){

            int idIndex = courseCursor.getColumnIndex("_id");
            int drugIdIndex = courseCursor.getColumnIndex("drug_id");
            int startIndex = courseCursor.getColumnIndex("start");
            int endIndex = courseCursor.getColumnIndex("end");
            int intervalIndex = courseCursor.getColumnIndex("interval");

            while(courseCursor.moveToNext()){

                int courseID = courseCursor.getInt(idIndex);
                Drug drug = new Drug(courseCursor.getInt(drugIdIndex));
                Instant start = new Instant(courseCursor.getLong(startIndex));
                Instant end = courseCursor.isNull(endIndex) ? null: new Instant(courseCursor.getLong(endIndex));
                List<LocalTime> takingTime = new ArrayList<>();
                int intervalInDays = courseCursor.getInt(intervalIndex);

                String[] argsForTakingTimeSelect = {String.valueOf(courseID)};
                Cursor takingTimeCursor = db.rawQuery("select time from taking_time where course_id = ?", argsForTakingTimeSelect);

                if(takingTimeCursor != null){

                    int timeIndex = takingTimeCursor.getColumnIndex("time");

                    while(takingTimeCursor.moveToNext()){
                        long millisOfDay = takingTimeCursor.getLong(timeIndex);
                        LocalTime time = LocalTime.fromMillisOfDay(millisOfDay);
                        takingTime.add(time);
                    }

                    takingTimeCursor.close();
                }

                Course course = new Course(courseID, drug, start, end, takingTime,intervalInDays);
                courses.add(course);
            }
            courseCursor.close();
        }
        return courses;
    }

    private void insertTakingTime(int courseID, List<LocalTime> takingTime){
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder selectForInsertTakingTimes = new StringBuilder();
        for(int i = 0; i < takingTime.size(); i++){
            if(i == 0){
                selectForInsertTakingTimes.append("select " + takingTime.get(i).getMillisOfDay() + "," + courseID);
            }
            else{
                selectForInsertTakingTimes.append(" union select " + takingTime.get(i).getMillisOfDay() + "," + courseID);
            }
        }
        db.execSQL("insert into taking_time(time, course_id) " + selectForInsertTakingTimes.toString());
    }

    private int insertCourse(Drug drug, Instant startDate, Instant endDate, int intervalInDays){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into course(drug_id, start, end, interval) " +
                "values(" + drug.getId() + "," + startDate.getMillis() + "," +
                (endDate == null ? null: endDate.getMillis()) + "," + intervalInDays + ")");
        Cursor cursor = db.rawQuery("select last_insert_rowid() inserted_id", null);
        cursor.moveToFirst();
        int courseID = cursor.getInt(cursor.getColumnIndex("inserted_id"));
        cursor.close();
        return courseID;
    }

    public Course addCourse(Drug drug, Instant startDate, Instant endDate, List<LocalTime> takingTime, int intervalInDays){
        int courseID = insertCourse(drug, startDate, endDate, intervalInDays);
        insertTakingTime(courseID, takingTime);
        return new Course(courseID, drug, startDate, endDate, takingTime, intervalInDays);
    }

    public void deleteCourse(int courseID){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(courseID)};
        db.delete("course", "_id = ?", args);
    }

    public List<Drug> getAllDrugs(){
        List<Drug> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"_id", "name"};
        Cursor cursor = db.query(false, "drug", columns, null, null, null, null, null, null);
        if(cursor != null){
            int idIndex = cursor.getColumnIndex("_id");
            int idName = cursor.getColumnIndex("name");
            while (cursor.moveToNext()){
                int drugID = cursor.getInt(idIndex);
                String name = cursor.getString(idName);
                result.add(new Drug(drugID, name));
            }
            cursor.close();
        }
        return result;
    }

    private String selectDrugAttr(String sql, int id, String attr){
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {String.valueOf(id)};
        Cursor result = db.rawQuery(sql, args);
        result.moveToFirst();
        int columnIndex = result.getColumnIndex(attr);
        String ans = result.getString(columnIndex);
        result.close();
        return ans;
    }

    public String getDrugNameByID(int id){
        return selectDrugAttr("select name from drug where _id = ?", id, "name");
    }

    public String getDrugFirmByID(int id) {
        return selectDrugAttr("select f.name from drug d, firm f where d.firm_id = f._id and d._id = ?", id, "name");
    }

    public String getDrugPharmGroupByID(int id) {
        return selectDrugAttr("select p.content from drug d, pharm_group p where d.pharm_group_id = p._id and d._id = ?", id, "content");
    }

    public String getDrugPharmActionByID(int id) {
        return selectDrugAttr("select p.content from drug d, pharm_action p where d.pharm_action_id = p._id and d._id = ?", id, "content");
    }

    public String getDrugUsageByID(int id) {
        return selectDrugAttr("select u.content from drug d, usage u where d.usage_id = u._id and d._id = ?", id, "content");
    }

    public String getDrugDosageByID(int id) {
        return selectDrugAttr("select dos.content from drug d, dosage dos where d.dosage_id = dos._id and d._id = ?", id, "content");
    }

    public String getDrugSideEffectByID(int id) {
        return selectDrugAttr("select s.content from drug d, side_effect s where d.side_effect_id = s._id and d._id = ?", id, "content");
    }

    public String getDrugContrasByID(int id) {
        return selectDrugAttr("select c.content from drug d, contras c where d.contras_id = c._id and d._id = ?", id, "content");
    }

    public String getDrugSpecialByID(int id) {
        return selectDrugAttr("select s.content from drug d, special s where d.special_id = s._id and d._id = ?", id, "content");
    }

    public String getDrugOverdoseByID(int id) {
        return selectDrugAttr("select o.content from drug d, overdose o where d.overdose_id = o._id and d._id = ?", id, "content");
    }

    public String getDrugInteractionByID(int id) {
        return selectDrugAttr("select i.content from drug d, interaction i where d.interaction_id = i._id and d._id = ?", id, "content");
    }

    public String getDrugStorageByID(int id) {
        return selectDrugAttr("select s.content from drug d, storage s where d.storage_id = s._id and d._id = ?", id, "content");
    }

    public String getDrugCompositionByID(int id) {
        return selectDrugAttr("select c.content from drug d, composition c where d.composition_id = c._id and d._id = ?", id, "content");
    }
}
