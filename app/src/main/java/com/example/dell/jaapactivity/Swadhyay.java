package com.example.dell.jaapactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.jaapactivity.Swadhaya.SwadhyayData;
import com.example.dell.jaapactivity.Swadhaya.SwadhyayDatabaseHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Swadhyay extends AppCompatActivity {
     EditText swadhyayTime;
     TextView swadhyayTextView;
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;
     TextView timerTextView;
     Button startSwadhyay;
     Button stopSwadhyay;
     Long time_in_minutes = 0l;
     Long time_in_milli = 0l;
     int id=0;
     MyCountdownTimer myCountdownTimer;
     public static final String SWADHYAYPREFERENCES = "TimePref";
     public static final String ENTERED_TIME = "time_entered";
    private static final String TAG = "SwadhyayActivity";
    SwadhyayDatabaseHandler sDb = new SwadhyayDatabaseHandler(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swadhyay);
        swadhyayTextView = findViewById(R.id.textViewSwadhyay);
        timerTextView = findViewById(R.id.swadhyayTimer);
        startSwadhyay = findViewById(R.id.startSwadhyay);
        stopSwadhyay = findViewById(R.id.stopSwadhyay);
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts,null);

        //Alert dialog builder
        AlertDialog.Builder alertDialogBuilder =  new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);

        swadhyayTime = promptsView.findViewById(R.id.editTextDialogUserInput);
        //shared preferences for storing time
        sharedPreferences = getSharedPreferences(SWADHYAYPREFERENCES, Context.MODE_PRIVATE);


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        id++;
                        swadhyayTextView.setText(swadhyayTime.getText());
                        time_in_minutes = Long.parseLong(String.valueOf(swadhyayTime.getText()));
                        time_in_milli = time_in_minutes * 60000;
                        Log.d(TAG, "onClick: Time in minutes: "+ time_in_minutes);
                        Log.d(TAG, "onClick: Time in milli seconds: "+ time_in_milli);
                        editor = sharedPreferences.edit();
                        editor.putLong(ENTERED_TIME,time_in_milli);
                        editor.apply();
                        long inserted = sDb.addSwadhyay(new SwadhyayData(time_in_minutes));
                        Log.d(TAG, "onClick: Inserted : "+inserted);
                        List<SwadhyayData> swadhyayDataList = sDb.getAllSwadhyayData();
                        for(SwadhyayData sd : swadhyayDataList){
                            String log = "Id : "+ sd.getId() + ", Time : "+ sd.getTime();
                            Log.d(TAG, "onClick: Data "+ log);
                        }


                    }
                });
        alertDialogBuilder
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

         startSwadhyay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startSwadhyay.setEnabled(false);
                 Log.d(TAG, "onClick: startSwadhyay button clicked ");
                    sharedPreferences = getSharedPreferences(SWADHYAYPREFERENCES,Context.MODE_PRIVATE);
                    Long timer_time = sharedPreferences.getLong(ENTERED_TIME,0);
                 Log.d(TAG, "onClick: time slected "+ timer_time);
                    myCountdownTimer = new MyCountdownTimer(timer_time,100);
                    myCountdownTimer.start();
             }
         });

         stopSwadhyay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startSwadhyay.setEnabled(true);
                 timerTextView.setText("00:00:00");
                 myCountdownTimer.cancel();
             }
         });

    }
    public class MyCountdownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public  MyCountdownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
           timerTextView.setText(hms);
        }
        @Override
        public void onFinish() {

        }
    }
}
