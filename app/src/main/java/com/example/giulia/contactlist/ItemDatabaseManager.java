package com.example.giulia.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

public class ItemDatabaseManager {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "items";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_CODE = "code";

    public ItemDatabaseManager(Context context) {
        this.context = context;
    }

    public ItemDatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String description, String username, String code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DESCRIPTION, description);
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_CODE, code);
        return contentValues;
    }

    public long createItem(String description, String username, String code) {
        ContentValues initialValues = createContentValues(description, username, code);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    /*public boolean updateItem(Contatto contatto) {
        ContentValues updateValues = createContentValues(contatto.getNome(), contatto.getNumero());
        //return database.update(DATABASE_TABLE, updateValues, KEY_NAME + "=" + contatto.getNome(), null) > 0;
        return database.delete(DATABASE_TABLE, updateValues, null);
    }*/

    public Cursor fetchAllItems() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }

    public Cursor readItem(int id) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "_id = '" + id + "'", null, null, null, null);
    }
}
