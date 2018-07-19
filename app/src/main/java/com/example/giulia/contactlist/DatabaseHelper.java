package com.example.giulia.contactlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giulia on 05/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mylist.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE_ITEM =
            "CREATE TABLE items(" +
                    ItemDatabaseManager.KEY_DESCRIPTION + " TEXT, " +
                    ItemDatabaseManager.KEY_USERNAME + " TEXT, " +
                    ItemDatabaseManager.KEY_CODE + " TEXT " +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemDatabaseManager.DATABASE_TABLE);
        onCreate(db);
    }
}
