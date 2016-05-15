package com.khasang.pillshelper.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

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
