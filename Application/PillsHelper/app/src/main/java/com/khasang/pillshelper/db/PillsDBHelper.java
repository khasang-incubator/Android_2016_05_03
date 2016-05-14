package com.khasang.pillshelper.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PillsDBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pills.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile PillsDBHelper instance = null;

    public static PillsDBHelper getInstance(Context context){
        if(instance == null){
            synchronized (PillsDBHelper.class){
                if(instance == null){
                    return new PillsDBHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private PillsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
