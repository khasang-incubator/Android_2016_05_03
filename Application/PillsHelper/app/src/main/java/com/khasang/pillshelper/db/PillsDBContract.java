package com.khasang.pillshelper.db;

import android.provider.BaseColumns;

public final class PillsDBContract {
    public PillsDBContract() {
    }

    public static abstract class Attr implements BaseColumns{
        public static final String TABLE_NAME = "attr";
        public static final String COLUMN_NAME = "name";
    }

    public static abstract class Type implements BaseColumns{
        public static final String TABLE_NAME = "type";
        public static final String COLUMN_NAME = "name";
    }

    public static abstract class Entity implements BaseColumns{
        public static final String TABLE_NAME = "entity";
        public static final String COLUMN_TYPE_ID = "type_id";
        public static final String COLUMN_NAME = "name";
    }

    public static abstract class Val implements BaseColumns{
        public static final String TABLE_NAME = "val";
        public static final String COLUMN_ATTR_ID = "attr_id";
        public static final String COLUMN_ENTITY_ID = "entity_id";
        public static final String COLUMN_VALUE = "value";
    }

    public static abstract class Ref implements BaseColumns{
        public static final String TABLE_NAME = "ref";
        public static final String COLUMN_ATTR_ID = "attr_id";
        public static final String COLUMN_ENTITY_ID = "entity_id";
        public static final String REF_ID = "ref_id";
    }
}
