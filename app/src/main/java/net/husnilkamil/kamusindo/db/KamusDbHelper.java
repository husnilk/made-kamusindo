package net.husnilkamil.kamusindo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KamusDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Kamus.db";

    private static final String SQL_CREATE_IND_ENTRIES =
            "CREATE TABLE "+ KamusContract.Indonesia.TABLE_NAME +" ("+
                    KamusContract.Indonesia.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
                    KamusContract.Indonesia.COLUMN_NAME_WORD+ " TEXT, " +
                    KamusContract.Indonesia.COLUMN_NAME_DEF+ " TEXT)";

    private static final String SQL_CREATE_ENG_ENTRIES =
            "CREATE TABLE "+ KamusContract.Inggris.TABLE_NAME +" ("+
                    KamusContract.Inggris.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
                    KamusContract.Inggris.COLUMN_NAME_WORD+ " TEXT, " +
                    KamusContract.Inggris.COLUMN_NAME_DEF+ " TEXT)";

    private static final String SQL_DELETE_ENG_ENTRIES = "DROP TABLE IF EXISTS " + KamusContract.Inggris.TABLE_NAME;
    private static final String SQL_DELETE_IND_ENTRIES = "DROP TABLE IF EXISTS " + KamusContract.Indonesia.TABLE_NAME;

    public KamusDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_IND_ENTRIES);
        db.execSQL(SQL_CREATE_ENG_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENG_ENTRIES);
        db.execSQL(SQL_DELETE_IND_ENTRIES);
    }

}
