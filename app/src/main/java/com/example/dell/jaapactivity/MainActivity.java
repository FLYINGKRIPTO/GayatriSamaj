package com.example.dell.jaapactivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Spinner time_spinner;
    MyCountdownTimer myCountdownTimer;
    TextView timer_text;
    Button startJap;
    Button stopJap;
    Button withGurudev;
    Button withMataji;
    TextView time_textView_store;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Time_in_minutes = "timeKey";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_spinner = findViewById(R.id.byTime);
        time_textView_store = findViewById(R.id.time_text);
        timer_text = findViewById(R.id.timer_textView);
        startJap = findViewById(R.id.startJap);
        withGurudev = findViewById(R.id.withPujyaGurudev);
        withMataji = findViewById(R.id.withMataji);
        stopJap = findViewById(R.id.stopJap);
        //creating adapter for spinner
        final ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.timelist,android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(timeAdapter);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                Long time = Long.parseLong(item);
                Long time_in_minutes = time*60000;
                time_textView_store.setText(time+"");

                editor = sharedPreferences.edit();
                editor.putLong(Time_in_minutes,time_in_minutes);
                editor.commit();
                Toast.makeText(getApplicationContext(),"You selected : "+time,Toast.LENGTH_SHORT).show();
            //    myCountdownTimer = new MyCountdownTimer(time_in_minutes,100);
             //   myCountdownTimer.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        withGurudev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoPlayerIntent = new Intent(MainActivity.this,VideoActivity.class);
                startActivity(videoPlayerIntent);

            }
        });
        withMataji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent videoPlayerIntent = new Intent(MainActivity.this,VideoActivity.class);
                 startActivity(videoPlayerIntent);
            }
        });
        startJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                Long timer_time = sharedPreferences.getLong(Time_in_minutes,10);
                myCountdownTimer = new MyCountdownTimer(timer_time,100);
                myCountdownTimer.start();
              /*) time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item = parent.getItemAtPosition(position).toString();
                        Long time = Long.parseLong(item);
                        Long time_in_minutes = time*60000;
                        time_textView_store.setText(time+"");
                        Toast.makeText(getApplicationContext(),"You selected : "+time,Toast.LENGTH_SHORT).show();
                        myCountdownTimer = new MyCountdownTimer(time_in_minutes,100);
                        myCountdownTimer.start();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/


            }
        });
        stopJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountdownTimer = new MyCountdownTimer(0,0);
                myCountdownTimer.start();

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
            timer_text.setText(hms);
            sendNotification(hms);
        }
        @Override
        public void onFinish() {

        }

        public void stopCountDown(){

        }


    }
    public void sendNotification(String timer){
        NotificationCompat.Builder mNotification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Jap Timer On")
                .setContentText(timer)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,mNotification.build());

    }



}
