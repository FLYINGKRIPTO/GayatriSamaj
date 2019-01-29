package com.example.dell.jaapactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private static final String TAG = "ReportActivity";
    ReportDataBaseHandler rDb = new ReportDataBaseHandler(this);
    JapDatabaseHandler jDb = new JapDatabaseHandler(this);
    MeditationDataBaseHandler mDb = new MeditationDataBaseHandler(this);
    int past_thirty_jap_time = 0;
    int past_thirty_med_time = 0;
    int past_thirty_swa_time = 0;
    int past_thirty_yag_time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        PieChart pieChart = findViewById(R.id.pieChart);
        PieChart japDataChart = findViewById(R.id.japDataPieChart);
        PieChart meditaionDataPieChart =  findViewById(R.id.meditationDataPieChart);
        PieChart timeComparisionChart = findViewById(R.id.timeCompare);
        BarChart dayWiseTimeChart  = findViewById(R.id.dayWiseBarChart);
        PieChart monthlyTimeChart = findViewById(R.id.monthlyReport);

        //Pie chart for past 30 days data
        PieChart pastThirtyDays = findViewById(R.id.pastThirtyDays);

        //Date is month
        //Time is date
        //and day is day
      /*   rDb.addUserReportData(new ReportData("Jap","Jan","21","Tue",4,2.6f,"Atam Bodh Dhayan","with Pujya Gurudev"));
        rDb.addUserReportData(new ReportData("Jap","Jan","21","Tue",4,2.6f,"Atam Bodh Dhayan","with Pujya Gurudev"));
        rDb.addUserReportData(new ReportData("Meditation","Dec","21","Sun",6,3.6f,"Panchkosh Dhyaan","with Pujya Mataji"));
        rDb.addUserReportData(new ReportData("Swadhyay","Jan","23","Wed",4,2.6f,"Atam Bodh Dhayan","by Time"));
        rDb.addUserReportData(new ReportData("Meditation","Jan","21","Tue",4,2.6f,"Naad yog Dhyaan","by Mala"));
        rDb.addUserReportData(new ReportData("Swadhyay","Dec","21","Tue",2,1.6f,"Amrit Varsha Dhyaan","with Pujya Gurudev"));
        rDb.addUserReportData(new ReportData("Jap","Jan","16","Wed",4,2.6f,"Jyoti Avdhrnam Dhyaan","with Pujya Gurudev"));
        rDb.addUserReportData(new ReportData("Jap","Jan","17","Sat",4,2.6f,"Sharir Dhyaan","with Pujya Gurudev"));
        rDb.addUserReportData(new ReportData("Yagya","Dec","24","Tue",4,2.6f,"Atam Bodh Dhayan","by Time"));
        rDb.addUserReportData(new ReportData("Jap","Jan","26","Tue",4,2.6f,"Atam Bodh Dhayan","by Mala"));

        */
      //Total modes Pie Chart
        ArrayList<PieEntry> totalModes = new ArrayList<>();
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
        pieChart.setUsePercentValues(true);



        //Jap data PieChart
        ArrayList<PieEntry> japDataList = new ArrayList<>();
        japDataList.add(new PieEntry((float)jDb.totalbyTime(),"by Time"));
        japDataList.add(new PieEntry((float)jDb.totalbyMala(),"by Mala"));
        japDataList.add(new PieEntry((float)jDb.totalwithGurudev(),"with Pujya Gurudev"));
        japDataList.add(new PieEntry((float)jDb.totalwithMataji(),"with Pujya Mataji"));

        PieDataSet japDataSet = new PieDataSet(japDataList,"Different Jap Modes");

        PieData Japdata = new PieData(japDataSet);
        japDataChart.setData(Japdata);
        japDataChart.setCenterText("Jap Modes");
        japDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        japDataChart.setEntryLabelColor(Color.BLACK);
        japDataSet.setSliceSpace(1f);
        japDataChart.animateXY(2000, 2000);


        //Meditation Data PieChart
        ArrayList<PieEntry> meditationDataList = new ArrayList<PieEntry>();
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
        meditationDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        meditaionDataPieChart.setEntryLabelColor(Color.BLACK);
        meditationDataSet.setSliceSpace(1f);
        meditaionDataPieChart.animateXY(2000, 2000);

        //Time Comparision pie chart

        ArrayList<PieEntry> timeCompareDataList = new ArrayList<PieEntry>();
        timeCompareDataList.add(new PieEntry(rDb.sumUserTime(),"Entered Time"));
        timeCompareDataList.add(new PieEntry(rDb.sumActualTime(), "Followed Time"));

        PieDataSet timeCompareDataSet = new PieDataSet(timeCompareDataList,"Time Comparision");
        PieData timeCompareData = new PieData(timeCompareDataSet);
        timeComparisionChart.setData(timeCompareData);
        timeComparisionChart.setCenterText("Entered Time V/S Followed Time");
        timeCompareDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        timeCompareDataSet.setSliceSpace(1f);
        timeComparisionChart.setEntryLabelColor(Color.BLACK);
        timeComparisionChart.animateXY(2000,2000);


        //Day Wise Bar Chart

        ArrayList<BarEntry> dayWiseTime = new ArrayList<BarEntry>();
        dayWiseTime.add(new BarEntry(1,rDb.totalDaysTime(0)));
        dayWiseTime.add(new BarEntry(2,rDb.totalDaysTime(1)));
        dayWiseTime.add(new BarEntry(3, rDb.totalDaysTime(2)));
        dayWiseTime.add(new BarEntry(4, rDb.totalDaysTime(3)));
        dayWiseTime.add(new BarEntry(5,rDb.totalDaysTime(4)));
        dayWiseTime.add(new BarEntry(6,rDb.totalDaysTime(5)));
        dayWiseTime.add(new BarEntry(7,rDb.totalDaysTime(6)));

        BarDataSet dayWiseTimeBar = new BarDataSet(dayWiseTime,"Day Wise Comparision");
        BarData barData = new BarData(dayWiseTimeBar);
        dayWiseTimeChart.setData(barData);
        dayWiseTimeBar.setLabel("Days");
        String [] labels = {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        dayWiseTimeBar.setStackLabels(labels);
        dayWiseTimeChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        dayWiseTimeBar.setColors(ColorTemplate.JOYFUL_COLORS);
        dayWiseTimeChart.animateXY(6000,6000);
        dayWiseTimeBar.setBarBorderColor(Color.BLACK);


        Calendar calender = Calendar.getInstance();
        Calendar calender2 = Calendar.getInstance();
        Log.d(TAG, "onCreate: Current Week : "+ calender.get(Calendar.WEEK_OF_YEAR));
        Log.d(TAG, "onCreate: Current Month : "+ calender.get(Calendar.MONTH)+1);
        Log.d(TAG, "onCreate: day of the month "+ calender.get(Calendar.DAY_OF_MONTH) );

        Date today = new Date();
        calender.setTime(today);
        Log.d(TAG, "onCreate: Today's Date "+ calender.get(Calendar.DATE));
        Log.d(TAG, "onCreate: Today's Month "+ calender.get(Calendar.MONTH));
        Log.d(TAG, "onCreate: Today's year "+ calender.get(Calendar.YEAR));

        calender2.add(Calendar.DAY_OF_MONTH, -30);

        Log.d(TAG, "onCreate: Date one Month before "+calender2.get(Calendar.DATE));
        Log.d(TAG, "onCreate: Month one month before "+ calender2.get(Calendar.MONTH));
        Log.d(TAG, "onCreate: Year one month before "+ calender2.get(Calendar.YEAR));

        Log.d(TAG, "onCreate:first method past one month Jap data : "+ rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),0));
        Log.d(TAG, "onCreate:first method past one month Meditation data : "+ rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),1));
        Log.d(TAG, "onCreate:first method past one month Swadhyay data : "+ rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),2));
        Log.d(TAG, "onCreate:first metod past one month Yagya data : "+ rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),3));

        // Integer c = (calender.get(Calendar.DATE));
      //  Log.d(TAG, "onCreate: Today's Date in Integer "+c);

        // data if month changes
        Log.d(TAG, "onCreate: second Method for past dates jap "+ rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),0));
        Log.d(TAG, "onCreate: second Method for past dates Med "+ rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),1));
        Log.d(TAG, "onCreate: second Method for past dates Swa "+ rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),2));
        Log.d(TAG, "onCreate: second Method for past dates Yag "+ rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),3));

      if(calender.get(Calendar.MONTH)!=calender2.get(Calendar.MONTH))
      {
           past_thirty_jap_time= rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),0)+
                  rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),0);
          past_thirty_med_time  = rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),1)+
                  rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),1);
          past_thirty_swa_time= rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),2)+
                  rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),2);
          past_thirty_yag_time  = rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),3)+
                  rDb.pastOneMonthDataB(calender2.get(Calendar.DATE),calender2.get(Calendar.MONTH),3);

          Log.d(TAG, "onCreate: sum total  past one month jap time "+past_thirty_jap_time);
          Log.d(TAG, "onCreate: sum total  one month meditaiton time "+ past_thirty_med_time);
          Log.d(TAG, "onCreate: sum total past one month swadhyay time "+ past_thirty_swa_time);
          Log.d(TAG, "onCreate: sum total past one month yagya time "+ past_thirty_yag_time);
      }

      else {
          past_thirty_jap_time  =rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),0);
          past_thirty_med_time = rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),1);
          past_thirty_swa_time = rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),2);
          past_thirty_yag_time = rDb.pastOneMonthDataF(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),3);
      }

        //   Log.d(TAG, "onCreate: previous month Jap data "+ rDb.oneMonthData(calender.get(Calendar.DATE),calender.get(Calendar.MONTH),0));
      //Past 30 days
        ArrayList<PieEntry> pastThirtyDataList = new ArrayList<PieEntry>();
        pastThirtyDataList.add(new PieEntry(past_thirty_jap_time,"JAP"));
        pastThirtyDataList.add(new PieEntry(past_thirty_med_time,"MEDITATION"));
        pastThirtyDataList.add(new PieEntry(past_thirty_swa_time,"SWADHYAY"));
        pastThirtyDataList.add(new PieEntry(past_thirty_yag_time,"YAGYA"));

        PieDataSet pastThirtyDataSet = new PieDataSet(pastThirtyDataList,"PAST THIRTY DAYS ");
        PieData pastThirtyData = new PieData(pastThirtyDataSet);
        pastThirtyDays.setData(pastThirtyData);
        pastThirtyDays.setCenterText("PAST THIRTY DAYS");
        pastThirtyDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pastThirtyDays.setEntryLabelColor(Color.BLACK);
        pastThirtyDataSet.setSliceSpace(1f);
        pastThirtyDays.animateXY(2000, 2000);

      /*  Date today1 = new Date();
        calender2.setTime(today1);
        Log.d(TAG, "onCreate: Today's Date "+ calender2.get(Calendar.DATE));
        calender2.add(Calendar.DAY_OF_WEEK,-7);
        Log.d(TAG, "onCreate: Date Starting week  "+ calender2.get(Calendar.DATE));
        */

        //Day Wise
        Log.d(TAG, "onCreate: total monday time "+ rDb.totalDaysTime(0));
        Log.d(TAG, "onCreate: total tuesday time "+ rDb.totalDaysTime(1));
        Log.d(TAG, "onCreate: total wednesday time "+ rDb.totalDaysTime(2));
        Log.d(TAG, "onCreate: total thursday time "+ rDb.totalDaysTime(3));
        Log.d(TAG, "onCreate: total Friday time "+ rDb.totalDaysTime(4));
        Log.d(TAG, "onCreate: total Saturday time "+ rDb.totalDaysTime(5));
        Log.d(TAG, "onCreate: total Sunday time"+ rDb.totalDaysTime(6));
        //Monthly Representation

            Log.d(TAG, "onCreate: total this months time "+ rDb.totalMonthTime(calender.get(Calendar.MONTH)));

            Log.d(TAG, "onCreate: total this months Japs "+ rDb.totalMonthModes(0,calender.get(Calendar.MONTH)));
            Log.d(TAG, "onCreate: total this months Meditations "+ rDb.totalMonthModes(1,calender.get(Calendar.MONTH)));
            Log.d(TAG, "onCreate: total this months Swadhyay " + rDb.totalMonthModes(2,calender.get(Calendar.MONTH)));
            Log.d(TAG, "onCreate: total this months Yagya "+ rDb.totalMonthModes(3,calender.get(Calendar.MONTH)));


            Log.d(TAG, "onCreate: Generalised total time of Japs in this month "+ rDb.totalTimeMonthModes(0,calender.get(Calendar.MONTH)));
        Log.d(TAG, "onCreate: Generalised total time of Meditations in this month "+ rDb.totalTimeMonthModes(1,calender.get(Calendar.MONTH)));
        Log.d(TAG, "onCreate: Generalised total time of Swadhyay in this month "+ rDb.totalTimeMonthModes(2,calender.get(Calendar.MONTH)));
        Log.d(TAG, "onCreate: Generalised total time of Yagya in January in this month"+ rDb.totalTimeMonthModes(3,calender.get(Calendar.MONTH)));

        //This month's time spent on different modes representation
        ArrayList<PieEntry> monthDataList = new ArrayList<PieEntry>();
        monthDataList.add(new PieEntry(rDb.totalTimeMonthModes(0,calender.get(Calendar.MONTH)),"Jap"));
        monthDataList.add(new PieEntry(rDb.totalTimeMonthModes(1,calender.get(Calendar.MONTH)), "Meditation"));
        monthDataList.add(new PieEntry(rDb.totalTimeMonthModes(2,calender.get(Calendar.MONTH)),"Swadhyay"));
        monthDataList.add(new PieEntry(rDb.totalTimeMonthModes(3,calender.get(Calendar.MONTH)),"Yagya"));

        PieDataSet monthDataSet = new PieDataSet(monthDataList,"Month's View");
        PieData monthData = new PieData(monthDataSet);
        monthlyTimeChart.setData(monthData);
        monthlyTimeChart.setCenterText("This months Review");
        monthDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        monthDataSet.setSliceSpace(1f);
        monthlyTimeChart.setEntryLabelColor(Color.BLACK);
        monthlyTimeChart.animateXY(2000,2000);



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
                    + ", Audio Name: "+ rp.getAudioName()//8
                    + ", Year : "+ rp.getYear();
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



         //TODO :: Weekly Data Representation
         //TODO :: Daily Data Representation





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.datePicker:

        }
        return super.onOptionsItemSelected(item);
    }
}
