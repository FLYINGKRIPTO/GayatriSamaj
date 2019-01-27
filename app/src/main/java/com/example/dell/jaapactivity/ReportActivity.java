package com.example.dell.jaapactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.jaapactivity.Jap.JapDatabaseHandler;
import com.example.dell.jaapactivity.Meditation.MeditationDataBaseHandler;
import com.example.dell.jaapactivity.ReportManager.ReportData;
import com.example.dell.jaapactivity.ReportManager.ReportDataBaseHandler;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private static final String TAG = "ReportActivity";
    ReportDataBaseHandler rDb = new ReportDataBaseHandler(this);
    JapDatabaseHandler jDb = new JapDatabaseHandler(this);
    MeditationDataBaseHandler mDb = new MeditationDataBaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        PieChart pieChart = findViewById(R.id.pieChart);
        PieChart japDataChart = findViewById(R.id.japDataPieChart);
        PieChart meditaionDataPieChart =  findViewById(R.id.meditationDataPieChart);

        //Total modes Pie Chart
        ArrayList totalModes = new ArrayList();
        totalModes.add(new PieEntry((float)rDb.totalJaps(),"Japs"));
        totalModes.add(new PieEntry((float)rDb.totalMeditations(),"Meditations"));
        totalModes.add(new PieEntry((float)rDb.totalSwadhyay(),"Swadhyay"));
        totalModes.add(new PieEntry((float)rDb.totalYagya(),"Yagya"));

        PieDataSet dataSet = new PieDataSet(totalModes,"Different Modes");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(3000, 3000);

        //Jap data PieChart
        ArrayList japDataList = new ArrayList();
        japDataList.add(new PieEntry((float)jDb.totalbyTime(),"by Time"));
        japDataList.add(new PieEntry((float)jDb.totalbyMala(),"by Mala"));
        japDataList.add(new PieEntry((float)jDb.totalwithGurudev(),"with Pujya Gurudev"));
        japDataList.add(new PieEntry((float)jDb.totalwithMataji(),"with Pujya Mataji"));

        PieDataSet japDataSet = new PieDataSet(japDataList,"Different Jap Modes");

        PieData Japdata = new PieData(japDataSet);
        japDataChart.setData(Japdata);
        japDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        japDataChart.animateXY(3000, 3000);


        //Meditation Data PieChart
        ArrayList meditationDataList = new ArrayList();
        meditationDataList.add(new PieEntry(mDb.totalSong1(),"Atam Bodh Dhyan"));
        meditationDataList.add(new PieEntry(mDb.totalSong2(),"Panchkosh Dhyaan"));
        meditationDataList.add(new PieEntry(mDb.totalSong3(),"Sharir Dhyaan"));
        meditationDataList.add(new PieEntry(mDb.totalSong4(),"Amrit Varsha Dhyaan"));
        meditationDataList.add(new PieEntry(mDb.totalSong5(),"Jyoti Avdhrnam Dhyaan"));
        meditationDataList.add(new PieEntry(mDb.totalSong6(),"Naad yog Dhyaan"));
        meditationDataList.add(new PieEntry(mDb.totalSong7(),"Tatv Bodh Dhyaan"));

        PieDataSet meditationDataSet = new PieDataSet(meditationDataList,"Meditation Tracks");
        PieData meditatationData = new PieData(meditationDataSet);
        meditaionDataPieChart.setData(meditatationData);
        meditationDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        meditaionDataPieChart.animateXY(3000, 3000);

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



        //Total User Time [DONE]
        //Total Actual Time [DONE]
        Log.d(TAG, "onCreate: sum user time :" + rDb.sumUserTime());
        Log.d(TAG, "onCreate: sum actual time: " + rDb.sumActualTime());

        //Total Meditations [DONE]
        //Total Japs [DONE]
        //Total Swadhyay [DONE]
        //Total Yagya [DONE]
        Log.d(TAG, "onCreate: total japs : "+ rDb.totalJaps());
        Log.d(TAG, "onCreate: total Meditations "+ rDb.totalMeditations());
        Log.d(TAG, "onCreate: total swadhyay "+ rDb.totalSwadhyay());
        Log.d(TAG, "onCreate: total yagya "+ rDb.totalYagya());

      //  Total by time [DONE]
      //  Total by mala [DONE]
      //  Total with Pujya Gurudev [DONE]
      //  Total with Mataji [DONE]

        Log.d(TAG, "onCreate: total by time "+ jDb.totalbyTime());
        Log.d(TAG, "onCreate: total by mala :"+ jDb.totalbyMala());
        Log.d(TAG, "onCreate: total with Pujya Gurudev :"+ jDb.totalwithGurudev());
        Log.d(TAG, "onCreate: total with Pujya Mataji :"+ jDb.totalwithMataji());


        //Total times - song 1
        //Total times - song 2
        //Total times - song 3
        //Total times - song 4
        //Total times - song 5
        //Total times - song 6
        //Total times - song 7

        Log.d(TAG, "onCreate: total song 1 "+ mDb.totalSong1());
        Log.d(TAG, "onCreate: total song 2 "+ mDb.totalSong2());
        Log.d(TAG, "onCreate: total song 3 "+ mDb.totalSong3());
        Log.d(TAG, "onCreate: total song 4 "+ mDb.totalSong4());
        Log.d(TAG, "onCreate: total song 5 "+ mDb.totalSong5());
        Log.d(TAG, "onCreate: total song 6 "+ mDb.totalSong6());
        Log.d(TAG, "onCreate: total song 7 "+ mDb.totalSong7());

    }




}
