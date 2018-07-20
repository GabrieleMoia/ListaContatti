package com.example.giulia.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CodeDatabaseManager {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "codes";
    public static final String KEY_ACCESS_CODE = "access_code";

    public CodeDatabaseManager(Context context) {
        this.context = context;
    }

    public CodeDatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String access_code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACCESS_CODE, access_code);
        return contentValues;
    }

    public long createItem(String access_code) {
        ContentValues initialValues = createContentValues(access_code);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchAllItems() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }

    public Cursor readItem(int id) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "_id = '" + id + "'", null, null, null, null);
    }
}
