package com.example.dell.jaapactivity.ReportManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_ACTUAL_TIME;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_AUDIO_NAME;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_DATE;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_DAY;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_ID;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_MODE;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_TIME;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_TYPE;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.KEY_USER_TIME;
import static com.example.dell.jaapactivity.ReportManager.ReportDataContractClass.WaitListEntryReport.TABLE_USER_REPORT;

public class ReportDataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userReportManager.db";
    public ReportDataBaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE_USER_REPORT = "CREATE TABLE "+
                TABLE_USER_REPORT + " ("
                +KEY_ID +"INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_MODE +"TEXT NOT NULL, "
                +KEY_USER_TIME +"INTEGER, "
                +KEY_ACTUAL_TIME +"INTEGER, "
                +KEY_DATE +"TEXT NOT NULL, "
                +KEY_TIME +"TEXT NOT NULL, "
                +KEY_DAY +"TEXT NOT NULL, "
                +KEY_TYPE +"TEXT, "
                +KEY_AUDIO_NAME +"TEXT); ";

        db.execSQL(SQL_CREATE_WAITLIST_TABLE_USER_REPORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER_REPORT);
        onCreate(db);

    }
    public long addUserReportData(ReportData reportData){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MODE,reportData.getMode());
        values.put(KEY_USER_TIME,reportData.getMode());
        values.put(KEY_ACTUAL_TIME,reportData.getMode());
        values.put(KEY_DATE,reportData.getMode());
        values.put(KEY_DAY,reportData.getMode());
        values.put(KEY_TIME,reportData.getTime());
        values.put(KEY_TYPE,reportData.getMode());
        values.put(KEY_AUDIO_NAME,reportData.getMode());

        return db.insert(TABLE_USER_REPORT,null,values);
    }
    public List<ReportData> getAllUserReportData() {
        List<ReportData> reportDataList = new ArrayList<ReportData>();

        String selectQuery = "SELECT * FROM "+TABLE_USER_REPORT;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                ReportData reportData = new ReportData();
                reportData.setId(Integer.parseInt(cursor.getString(0)));
                reportData.setMode(cursor.getString(1));
                reportData.setUserTime(Integer.parseInt(cursor.getString(2)));
                reportData.setActualTime(Integer.parseInt(cursor.getString(3)));
                reportData.setDate(cursor.getString(4));
                reportData.setDay(cursor.getString(5));
                reportData.setTime(cursor.getString(6));
                reportData.setType(cursor.getString(7));
                reportData.setAudioName(cursor.getString(8));
            }
            while (cursor.moveToNext());

        }
        return reportDataList;
    }
}
