package com.example.dell.jaapactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.jaapactivity.Jap.JapDatabaseHandler;
import com.example.dell.jaapactivity.Meditation.MeditationDataBaseHandler;
import com.example.dell.jaapactivity.ReportManager.ReportData;
import com.example.dell.jaapactivity.ReportManager.ReportDataBaseHandler;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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
        PieChart timeComparisionChart = findViewById(R.id.timeCompare);
        BarChart dayWiseTimeChart  = findViewById(R.id.dayWiseBarChart);

        //Total modes Pie Chart
        ArrayList totalModes = new ArrayList();
        totalModes.add(new PieEntry((float)rDb.totalJaps(),"Japs"));
        totalModes.add(new PieEntry((float)rDb.totalMeditations(),"Meditations"));
        totalModes.add(new PieEntry((float)rDb.totalSwadhyay(),"Swadhyay"));
        totalModes.add(new PieEntry((float)rDb.totalYagya(),"Yagya"));

        PieDataSet dataSet = new PieDataSet(totalModes,"Different Modes");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setCenterText("Modes");
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieChart.setEntryLabelColor(Color.BLACK);
        dataSet.setSliceSpace(1f);
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
        japDataChart.setCenterText("Jap Modes");
        japDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        japDataChart.setEntryLabelColor(Color.WHITE);
        japDataSet.setSliceSpace(1f);
        japDataChart.animateXY(2000, 2000);


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
        meditaionDataPieChart.setCenterText("Meditation Audios");
        meditationDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        meditationDataSet.setSliceSpace(1f);
        meditaionDataPieChart.animateXY(2000, 2000);

        //Time Comparision pie chart

        ArrayList timeCompareDataList = new ArrayList();
        timeCompareDataList.add(new PieEntry(rDb.sumUserTime(),"Entered Time"));
        timeCompareDataList.add(new PieEntry(rDb.sumActualTime(), "Followed Time"));

        PieDataSet timeCompareDataSet = new PieDataSet(timeCompareDataList,"Time Comparision");
        PieData timeCompareData = new PieData(timeCompareDataSet);
        timeComparisionChart.setData(timeCompareData);
        timeComparisionChart.setCenterText("Entered Time V/S Followed Time");
        timeCompareDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        timeCompareDataSet.setSliceSpace(1f);
        timeComparisionChart.animateXY(2000,2000);


        //Day Wise Bar Chart

        ArrayList dayWiseTime = new ArrayList();
        dayWiseTime.add(new BarEntry(1,11));
        dayWiseTime.add(new BarEntry(2,33));
        dayWiseTime.add(new BarEntry(3,4));
        dayWiseTime.add(new BarEntry(4,6));
        dayWiseTime.add(new BarEntry(5,17));
        dayWiseTime.add(new BarEntry(6,33));
        dayWiseTime.add(new BarEntry(7,rDb.totalSunTime()));

        BarDataSet dayWiseTimeBar = new BarDataSet(dayWiseTime,"Day Wise Comparision");
        BarData barData = new BarData(dayWiseTimeBar);
        dayWiseTimeChart.setData(barData);
        dayWiseTimeBar.setLabel("Days");
        String [] labels = {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        dayWiseTimeBar.setStackLabels(labels);
        dayWiseTimeChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        dayWiseTimeBar.setColors(ColorTemplate.COLORFUL_COLORS);
        dayWiseTimeChart.animateXY(6000,6000);


        //Today's Dashboard


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

        //Total sunday time
        Log.d(TAG, "onCreate: total sunday time "+ rDb.totalSunTime());
        Log.d(TAG, "onCreate: total monday time "+ rDb.totalMonTime());
        Log.d(TAG, "onCreate: total Tuesday time "+ rDb.totalTueTime());
        Log.d(TAG, "onCreate: total Wednesday time "+ rDb.totalWedTime());
        Log.d(TAG, "onCreate: total Thursday time "+ rDb.totalThurTime());
        Log.d(TAG, "onCreate: total Friday time "+ rDb.totalFriTime());
        Log.d(TAG, "onCreate: total Saturday time "+ rDb.totalSatTime());


    }




}
