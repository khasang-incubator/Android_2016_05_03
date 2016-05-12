package com.khasang.pillshelper.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PillsDBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pills.db";
    private static final int DATABASE_VERSION = 1;

    public PillsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
