package com.khasang.pillshelper.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.Drug;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

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
                Instant end = new Instant(courseCursor.getLong(endIndex));
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
        db.close();
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
        db.close();
    }

    private int insertCourse(Drug drug, Instant startDate, Instant endDate, int intervalInDays){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into course(drug_id, start, end, interval) " +
                "values(" + drug.getId() + "," + startDate.getMillis() + "," + endDate.getMillis() + "," + intervalInDays + ")");
        String[] columns = {"inserted_id"};
        Cursor cursor = db.rawQuery("select last_insert_rowid() inserted_id", columns);
        cursor.moveToFirst();
        int courseID = cursor.getInt(cursor.getColumnIndex("inserted_id"));
        cursor.close();
        db.close();
        return courseID;
    }

    public Course addCourse(Drug drug, Instant startDate, Instant endDate, List<LocalTime> takingTime, int intervalInDays){
        int courseID = insertCourse(drug, startDate, endDate, intervalInDays);
        insertTakingTime(courseID, takingTime);
        return new Course(courseID, drug, startDate, endDate, takingTime, intervalInDays);
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
        db.close();
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
        db.close();
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
