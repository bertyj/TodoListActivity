
package com.javatechig.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "TODOS";

    // Table columns(列)
    public static final String _ID = "_id";
    public static final String TODO_SUBJECT = "subject";
    public static final String TODO_DESC = "description";

    /**
     * Database names must be unique within an application, not across all applications.
     */
    // Database Information
    static final String DB_NAME = "JAVATECHIG_TODOS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_SUBJECT + " TEXT NOT NULL, " + TODO_DESC + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * The onCreate() method will be called on first time use of the application. This method is
     * called only if the database file is not created before. Once the database is successfully
     * created, it creates .DB files in your "data/data/<your app package name>/databases/"folder.
     * You can view this from DDMS from your emulator, but if you use your physical device you may
     * not be able to browse this file unless your device is rooted.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * The onUpgrade() method is called only when the database version is changed. Database version
     * is an integer value which is specified inside the DBhelper constructor.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
