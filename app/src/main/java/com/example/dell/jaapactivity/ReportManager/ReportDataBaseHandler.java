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
                TABLE_USER_REPORT + "( "
                +KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_MODE +" TEXT NOT NULL, "
                +KEY_USER_TIME +" INTEGER, "
                +KEY_ACTUAL_TIME +" INTEGER, "
                +KEY_DATE +" TEXT NOT NULL, "
                +KEY_TIME +" TEXT NOT NULL, "
                +KEY_DAY +" TEXT NOT NULL, "
                +KEY_TYPE +" TEXT, "
                +KEY_AUDIO_NAME +" TEXT); ";

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
        values.put(KEY_USER_TIME,reportData.getUserTime());
        values.put(KEY_ACTUAL_TIME,reportData.getActualTime());
        values.put(KEY_DATE,reportData.getDate());
        values.put(KEY_DAY,reportData.getDay());
        values.put(KEY_TIME,reportData.getTime());
        values.put(KEY_TYPE,reportData.getType());
        values.put(KEY_AUDIO_NAME,reportData.getAudioName());

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
                reportData.setActualTime(Float.parseFloat(cursor.getString(3)));
                reportData.setDate(cursor.getString(4));
                reportData.setTime(cursor.getString(5));
                reportData.setDay(cursor.getString(6));
                reportData.setType(cursor.getString(7));
                reportData.setAudioName(cursor.getString(8));
                reportDataList.add(reportData);

            }
            while (cursor.moveToNext());

        }
        return reportDataList;
    }

    public int getLastId(){
        String query = "SELECT "+KEY_ID +" from "+TABLE_USER_REPORT+ " order by "+KEY_ID+" DESC limit 1";
        SQLiteDatabase db = getWritableDatabase();
        int lastId=1;
        Cursor c = db.rawQuery(query,null);
        if(c!=null && c.moveToFirst()){
           lastId = c.getInt(0);

        }
        return lastId;
    }

    public boolean updateData(String id,float actualTime){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(KEY_ACTUAL_TIME,actualTime);
        db.update(TABLE_USER_REPORT,cn," id = ?",new String[] { id });
       return true;
    }

    public float sumUserTime(){
         float total = 0f;
        SQLiteDatabase db = this.getWritableDatabase();
        String sumQuery = " SELECT SUM(" + KEY_USER_TIME +") as Total FROM "+ TABLE_USER_REPORT;
        Cursor c = db.rawQuery(sumQuery,null);
        if(c.moveToFirst()){
             total = c.getInt(c.getColumnIndex("Total"));

        }
        c.close();
        return total;

    }

    public float sumActualTime(){
        float total = 0f;
        SQLiteDatabase db = getWritableDatabase();
        String sumQuery = " SELECT SUM(" + KEY_ACTUAL_TIME +") as Total FROM "+ TABLE_USER_REPORT;
        Cursor c = db.rawQuery(sumQuery,null);
        if(c.moveToFirst()){
            total = c.getInt(c.getColumnIndex("Total"));

        }
       c.close();
        return total;
    }

    public int totalJaps() {
        int totaljaps = 0;
        SQLiteDatabase db= getWritableDatabase();
        String japQuery = " SELECT "+ KEY_MODE + " FROM " + TABLE_USER_REPORT +
                " WHERE " + KEY_MODE + "= 'Jap'";
        Cursor cursor = db.rawQuery(japQuery,null);
        totaljaps = cursor.getCount();
        cursor.close();
        return  totaljaps;

    }
    public int totalMeditations() {
        int totaljaps = 0;
        SQLiteDatabase db= getWritableDatabase();
        String japQuery = " SELECT "+ KEY_MODE + " FROM " + TABLE_USER_REPORT +
                " WHERE " + KEY_MODE + "= 'Meditation'";
        Cursor cursor = db.rawQuery(japQuery,null);
        totaljaps = cursor.getCount();
        cursor.close();
        return  totaljaps;

    }
    public int totalSwadhyay() {
        int totaljaps = 0;
        SQLiteDatabase db= getWritableDatabase();
        String japQuery = " SELECT "+ KEY_MODE + " FROM " + TABLE_USER_REPORT +
                " WHERE " + KEY_MODE + "= 'Swadhyay'";
        Cursor cursor = db.rawQuery(japQuery,null);
        totaljaps = cursor.getCount();
        cursor.close();
        return  totaljaps;

    }
    public int totalYagya() {
        int totaljaps = 0;
        SQLiteDatabase db= getWritableDatabase();
        String japQuery = " SELECT "+ KEY_MODE + " FROM " + TABLE_USER_REPORT +
                " WHERE " + KEY_MODE + "= 'Yagya'";
        Cursor cursor = db.rawQuery(japQuery,null);
        totaljaps = cursor.getCount();
        cursor.close();
        return  totaljaps;

    }



}
