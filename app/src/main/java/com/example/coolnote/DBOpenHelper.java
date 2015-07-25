package com.example.coolnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{ // SQLiteOpenHelper is a member of android SDK, used to define database and manage connection

    //Constants for db name and version
    private static final String DATABASE_NAME = "notes.db"; // Database name
    private static final int DATABASE_VERSION = 1; // always set to 1, first time you create database

    //Constants for identifying table and columns
    public static final String TABLE_NOTES = "notes";  // table name
    public static final String NOTE_ID = "_id"; // columns, when content provider is managing, the expected primary key must start with "_"
    public static final String NOTE_TEXT = "noteText"; // body of the node
    public static final String NOTE_CREATED = "noteCreated"; // data time value

    public static final String[] ALL_COLUMNS =
            {NOTE_ID, NOTE_TEXT, NOTE_CREATED};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTES + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    public DBOpenHelper(Context context) {  // Constructor method
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // (context, name, factory, version)!
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // first time class is instantiated.
        db.execSQL(TABLE_CREATE); // re-built database with new structure
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db); //db is the database object.
    }
}
