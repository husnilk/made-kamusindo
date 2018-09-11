package net.husnilkamil.kamusindo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.husnilkamil.kamusindo.db.Kamus;
import net.husnilkamil.kamusindo.db.KamusContract.Indonesia;
import net.husnilkamil.kamusindo.db.KamusDbHelper;

import java.util.ArrayList;

public class KamusHelper {

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

    public ArrayList<Kamus> searchIndo(String nama) {
        String result = "";
        Cursor cursor = db.query(
                Indonesia.TABLE_NAME,
                null,
                Indonesia.COLUMN_NAME_WORD + " LIKE ?",
                new String[]{nama},
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
        return arrayList;
    }

    public long insertIndo(Kamus kamus) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Indonesia.COLUMN_NAME_WORD, kamus.getWord());
        initialValues.put(Indonesia.COLUMN_NAME_DEF, kamus.getDefinition());
        return db.insert(Indonesia.TABLE_NAME, null, initialValues);
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

    public void insertIndoTransaction(Kamus kamus) {
        String sql = "INSERT INTO " + Indonesia.TABLE_NAME + " (" + Indonesia.COLUMN_NAME_WORD + ", " + Indonesia.COLUMN_NAME_DEF + ") VALUES (?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, kamus.getWord());
        stmt.bindString(2, kamus.getDefinition());
        stmt.execute();
        stmt.clearBindings();

    }

}
