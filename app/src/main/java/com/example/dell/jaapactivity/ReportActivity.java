package com.example.dell.jaapactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.jaapactivity.Jap.JapDatabaseHandler;
import com.example.dell.jaapactivity.ReportManager.ReportData;
import com.example.dell.jaapactivity.ReportManager.ReportDataBaseHandler;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private static final String TAG = "ReportActivity";
    ReportDataBaseHandler rDb = new ReportDataBaseHandler(this);
    JapDatabaseHandler jDb = new JapDatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        List<ReportData> reportDataList = rDb.getAllUserReportData();
        Log.d(TAG, "onClick: "+reportDataList);
        for (ReportData rp : reportDataList) {
            Log.d(TAG, "onClick: For loop");
            String reportLog = "Id: "+rp.getId() //0
                    + ", Mode: "+ rp.getMode()   //1
                    + ", User Time: "+ rp.getUserTime() //2
                    + ", Actual Time: "+ rp.getActualTime() //3
                    + ", Date : "+rp.getDate() //4
                    + ", Time : "+rp.getTime()  //5
                    + ", Day: "+rp.getDay()  //6
                    + ", Type: "+rp.getType() //7
                    + ", Audio Name: "+ rp.getAudioName();//8
            Log.d("Report Activity :",reportLog);
        }

        Log.d(TAG, "onCreate: sum user time :" + rDb.sumUserTime());
        Log.d(TAG, "onCreate: sum actual time: " + rDb.sumActualTime());
        Log.d(TAG, "onCreate: total japs : "+ rDb.totalJaps());
        Log.d(TAG, "onCreate: total Meditations "+ rDb.totalMeditations());
        Log.d(TAG, "onCreate: total swadhyay "+ rDb.totalSwadhyay());
        Log.d(TAG, "onCreate: total yagya "+ rDb.totalYagya());
        Log.d(TAG, "onCreate: total by time "+ jDb.totalbyTime());
        Log.d(TAG, "onCreate: total by mala :"+ jDb.totalbyMala());
        Log.d(TAG, "onCreate: total with Pujya Gurudev :"+ jDb.totalwithGurudev());
        Log.d(TAG, "onCreate: total with Pujya Mataji :"+ jDb.totalwithMataji());

    }




}
