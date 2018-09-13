package net.husnilkamil.kamusindo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.util.Log;

import net.husnilkamil.kamusindo.db.KamusContract.Indonesia;
import net.husnilkamil.kamusindo.db.KamusContract.Inggris;

import java.util.ArrayList;

public class KamusHelper {

    public final static int IND = 1;
    public final static int ENG = 2;
    private static final String TAG = KamusHelper.class.getSimpleName();

    private Context context;
    private KamusDbHelper dbHelper;

    private SQLiteDatabase db;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dbHelper = new KamusDbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Kamus> search(int table, String word){
        String tableName = getTableName(table);

        String query = "%"+word+"%";
        String result = "";
        Cursor cursor = db.query(
                tableName,
                null,
                Indonesia.COLUMN_NAME_WORD + " LIKE ?",
                new String[]{query},
                null,
                null,
                Indonesia.COLUMN_NAME_ID + " ASC",
                null);
        cursor.moveToFirst();

        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Indonesia.COLUMN_NAME_ID)));
                kamus.setWord(cursor.getString(cursor.getColumnIndexOrThrow(Indonesia.COLUMN_NAME_WORD)));
                kamus.setDefinition(cursor.getString(cursor.getColumnIndexOrThrow(Indonesia.COLUMN_NAME_DEF)));

                arrayList.add(kamus);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();

        Log.d(TAG, "Jumlah data " + arrayList.size());
        return arrayList;
    }

    public long insert(int table, Kamus kamus) {
        String tableName = getTableName(table);
        ContentValues initialValues = new ContentValues();
        initialValues.put(Indonesia.COLUMN_NAME_WORD, kamus.getWord());
        initialValues.put(Indonesia.COLUMN_NAME_DEF, kamus.getDefinition());
        return db.insert(tableName, null, initialValues);
    }


    public void beginTransaction() {
        db.beginTransaction();
    }

    public void setTransactionSuccess() {
        db.setTransactionSuccessful();
    }

    public void endTransaction() {
        db.endTransaction();
    }

    public void insertTransaction(int table, Kamus kamus) {
        String tableName = getTableName(table);
        String sql = "INSERT INTO " + tableName + " (" + Indonesia.COLUMN_NAME_WORD + ", " + Indonesia.COLUMN_NAME_DEF + ") VALUES (?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, kamus.getWord());
        stmt.bindString(2, kamus.getDefinition());
        stmt.execute();
        stmt.clearBindings();

    }

    @NonNull
    private String getTableName(int table) {
        String tableName;
        if(table == IND){
            tableName = Indonesia.TABLE_NAME;
        }else{
            tableName = Inggris.TABLE_NAME;
        }
        return tableName;
    }

}
