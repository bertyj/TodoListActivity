
package com.javatechig.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * It is always a best practice to define an CONTROLLER or separate class for all your database
 * operations. Here in this example we are creating an new class that performs all database related
 * operations like add, update, delete records into table.
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private final Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    /**
     * Before performing any database operations like add, update, delete records in a table, first
     * open the database by calling getWritableDatabase() method.
     */
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Make sure to close database connection once it is opened.
     */
    // Close Database Connection
    public void close() {
        dbHelper.close();
    }

    // Add New todo Record
    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TODO_SUBJECT, name);
        contentValue.put(DatabaseHelper.TODO_DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Fetching All Records
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TODO_SUBJECT, DatabaseHelper.TODO_DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Modify Record
    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TODO_SUBJECT, name);
        contentValues.put(DatabaseHelper.TODO_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    // Delete Record
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
