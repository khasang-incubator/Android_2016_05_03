package com.khasang.pillshelper.db.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khasang.pillshelper.db.PillsDBHelper;

public class Drug extends ContextWrapper{
    private int id;
    private String name;
    private String firm;
    private String pharmGroup;
    private String pharmAction;
    private String usage;
    private String dosage;
    private String sideEffect;
    private String contras;
    private String special;
    private String overdose;
    private String interaction;
    private String storage;
    private String composition;

    private String selectAttr(String sql, String attr){
        SQLiteOpenHelper dbHelper = PillsDBHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {String.valueOf(id)};
        Cursor result = db.rawQuery(sql, args);
        result.moveToFirst();
        int columnIndex = result.getColumnIndex(attr);
        String ans = result.getString(columnIndex);
        db.close();
        return ans;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if(name == null){
            name = selectAttr("select name from drug where _id = ?", "name");
        }
        return name;
    }

    public String getFirm() {
        if(firm == null){
            firm = selectAttr("select f.name from drug d, firm f where d.firm_id = f._id and d._id = ?", "name");
        }
        return firm;
    }

    public String getPharmGroup() {
        if(pharmGroup == null){
            pharmGroup = selectAttr("select p.content from drug d, pharm_group p where d.pharm_group_id = p._id and d._id = ?", "content");
        }
        return pharmGroup;
    }

    public String getPharmAction() {
        if(pharmAction == null){
            pharmAction = selectAttr("select p.content from drug d, pharm_action p where d.pharm_action_id = p._id and d._id = ?", "content");
        }
        return pharmAction;
    }

    public String getUsage() {
        if(usage == null){
            usage = selectAttr("select u.content from drug d, usage u where d.usage_id = u._id and d._id = ?", "content");
        }
        return usage;
    }

    public String getDosage() {
        if(dosage == null){
            dosage = selectAttr("select dos.content from drug d, dosage dos where d.dosage_id = dos._id and d._id = ?", "content");
        }
        return dosage;
    }

    public String getSideEffect() {
        if(sideEffect == null){
            sideEffect = selectAttr("select s.content from drug d, side_effect s where d.side_effect_id = s._id and d._id = ?", "content");
        }
        return sideEffect;
    }

    public String getContras() {
        if(contras == null){
            contras = selectAttr("select c.content from drug d, contras c where d.contras_id = c._id and d._id = ?", "content");
        }
        return contras;
    }

    public String getSpecial() {
        if(special == null){
            special = selectAttr("select s.content from drug d, special s where d.special_id = s._id and d._id = ?", "content");
        }
        return special;
    }

    public String getOverdose() {
        if(overdose == null){
            overdose = selectAttr("select o.content from drug d, overdose o where d.overdose_id = o._id and d._id = ?", "content");
        }
        return overdose;
    }

    public String getInteraction() {
        if(interaction == null){
            interaction = selectAttr("select i.content from drug d, interaction i where d.interaction_id = i._id and d._id = ?", "content");
        }
        return interaction;
    }

    public String getStorage() {
        if(storage == null){
            storage = selectAttr("select s.content from drug d, storage s where d.storage_id = s._id and d._id = ?", "content");
        }
        return storage;
    }

    public String getComposition() {
        if(composition == null){
            composition = selectAttr("select c.content from drug d, composition c where d.composition_id = c._id and d._id = ?", "content");
        }
        return composition;
    }

    public Drug(Context context, int id){
        super(context);
        this.id = id;
    }
}
