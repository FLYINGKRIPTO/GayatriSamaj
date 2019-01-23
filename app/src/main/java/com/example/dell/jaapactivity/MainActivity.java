package com.example.dell.jaapactivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Spinner options_spinner;
    MyCountdownTimer myCountdownTimer;
    TextView timer_text;
    Button startJap;
    Long time_in_minutes = 0l;
    Long time_in_milli = 0l;
    Button stopJap;
    TextView time_textView_store;
    TextView display_time_selcted;
    SharedPreferences sharedPreferences;
    SharedPreferences optionSelectedPreference;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor optionEditor;
    AlertDialog.Builder builder;
    String selectedItemFromOptions;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String OPTION_PREFERENCE = "OptionPref";
    public static final String selcted_item = "item_selected";
    public static final String Time_in_minutes = "timeKey";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_textView_store = findViewById(R.id.time_text);
        timer_text = findViewById(R.id.timer_textView);
        startJap = findViewById(R.id.startJap);
        stopJap = findViewById(R.id.stopJap);
        display_time_selcted = findViewById(R.id.display_selected_time);
        options_spinner = findViewById(R.id.options);
        //creating adapter for spinner


        final ArrayAdapter<CharSequence> optionsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.japOptions,android.R.layout.simple_spinner_item);
        optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options_spinner.setAdapter(optionsAdapter);

        builder = new AlertDialog.Builder(this);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        optionSelectedPreference = getSharedPreferences(OPTION_PREFERENCE,Context.MODE_PRIVATE);

   /*     time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
*/
        options_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item = parent.getItemAtPosition(position).toString();
                optionEditor = optionSelectedPreference.edit();
                optionEditor.putString(selcted_item,item);
                optionEditor.commit();
             //   Toast.makeText(MainActivity.this,"You selected "+item,Toast.LENGTH_SHORT).show();
                final String[] time = {"5","10","15","20","25","30"};
                if(item.equalsIgnoreCase("by Time")){
                  //  Toast.makeText(MainActivity.this,"by Time Clicked"+item,Toast.LENGTH_SHORT).show();
                    builder.setTitle("Choose your time for Jap");
                    builder.setItems(time, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            time_in_minutes = Long.parseLong(time[which]);
                            display_time_selcted.setVisibility(View.VISIBLE);
                            display_time_selcted.setText((time_in_minutes).toString()+" Minutes ");
                            time_in_milli = time_in_minutes*60000;
                            Log.d(TAG, "onClick: Time selected :" + time_in_milli + " milliseconds");
                            editor = sharedPreferences.edit();
                            editor.putLong(Time_in_minutes,time_in_milli);
                            editor.commit();

                        }

                    });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.setTitle("Choose");
                   alertDialog.show();

                }
              /*  else if(item.equalsIgnoreCase("with Pujya Gurudev")){
                    Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                    startActivity(intent);
                }
                else if(item.equalsIgnoreCase("with Pujya Mataji")){
                    Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                    startActivity(intent);
                }
           */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelectedPreference = getSharedPreferences(OPTION_PREFERENCE,Context.MODE_PRIVATE);
                selectedItemFromOptions = optionSelectedPreference.getString(selcted_item,"Choose Options");
                Log.d(TAG, "onClick: Selected Item "+ selectedItemFromOptions);

               if(selectedItemFromOptions.equalsIgnoreCase("by Time")){

                   sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                   Long timer_time = sharedPreferences.getLong(Time_in_minutes,10);
                   myCountdownTimer = new MyCountdownTimer(timer_time,100);
                   myCountdownTimer.start();

               }
               else if(selectedItemFromOptions.equalsIgnoreCase("with Pujya Gurudev")){
                   display_time_selcted.setVisibility(View.INVISIBLE);
                   Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                   startActivity(intent);
               }
               else if(selectedItemFromOptions.equalsIgnoreCase("with Pujya Mataji")){
                   display_time_selcted.setVisibility(View.INVISIBLE);
                   Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                   startActivity(intent);
               }



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
                timer_text.setText("00:00:00");
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
            timer_text.setText(hms);
            sendNotification(hms);
        }
        @Override
        public void onFinish() {

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
