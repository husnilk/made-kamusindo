package net.husnilkamil.kamusindo.db;

import android.provider.BaseColumns;

public final class KamusContract {

    private KamusContract(){}

    public static class Indonesia implements BaseColumns{

        public static final String TABLE_NAME = "indonesia";

        public static final String COLUMN_NAME_ID = "_ID";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_DEF = "definition";
    }

    public static class Inggris implements BaseColumns{

        public static final String TABLE_NAME = "inggris";

        public static final String COLUMN_NAME_ID = "_ID";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_DEF = "definition";
    }
}






