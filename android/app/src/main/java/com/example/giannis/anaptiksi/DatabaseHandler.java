package com.example.giannis.anaptiksi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.giannis.anaptiksi.Pojo.AndroidJobs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ubundistas on 24/1/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AndroidJobsManager";

    // Contacts table name
    private static final String TABLE_NMAPJOBS = "nmapjobs";

    // Contacts Table Columns names
    private static final String KEY_ID = "idnmapjobs";
    private static final String KEY_NAME = "nmapjobscol";
    private static final String KEY_FLAG = "flagperiodic";
    private static final String KEY_TIME= "timeperiodic";
    private static final String KEY_HASH = "SA_hashkey";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NMAPJOBS_TABLE = "CREATE TABLE " + TABLE_NMAPJOBS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_FLAG + " TEXT,"+ KEY_TIME + " TEXT,"
                + KEY_HASH + " TEXT" + ")";
        db.execSQL(CREATE_NMAPJOBS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NMAPJOBS);

        // Create tables again
        onCreate(db);
    }

    public void addAndroidJobs(AndroidJobs nmapjob) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, nmapjob.getIdnmapjobs());
        values.put(KEY_NAME, nmapjob.getNmapjobscol()); // Contact Name
        values.put(KEY_FLAG, nmapjob.getFlagperiodic()); // Contact Phone Number
        values.put(KEY_TIME, nmapjob.getTimeperiodic());
        values.put(KEY_HASH, nmapjob.getSa_hashkey());
        // Inserting Row
        db.insert(TABLE_NMAPJOBS ,null, values);
        db.close(); // Closing database connection
    }





    public List<AndroidJobs> getAndroidJobs() {
        String countQuery = "SELECT  * FROM " + TABLE_NMAPJOBS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        List<AndroidJobs> mylist=new LinkedList<>();


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            mylist.add(new AndroidJobs(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();

        return mylist;
    }

    public void deleteAndroidJobs(AndroidJobs nmapJob) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NMAPJOBS);
        db.close();
    }
}
