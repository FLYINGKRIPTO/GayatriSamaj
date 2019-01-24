package com.example.dell.jaapactivity;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.Toast;
import android.widget.VideoView;

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
    TextView display_time_selected;
    SharedPreferences sharedPreferences;
    SharedPreferences optionSelectedPreference;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor optionEditor;
    AlertDialog.Builder builder;
    String selectedItemFromOptions;
    String videoUrl = null;
    String item = null;
    Button meditationActivity;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String OPTION_PREFERENCE = "OptionPref";
    public static final String selected_item = "item_selected";
    public static final String Time_in_minutes = "timeKey";
    private static final String TAG = "MainActivity";

    // Instance Variables from video activity
    TextView timerTextView;
    VideoView videoView;
    TextView timeInMilliTextView;;
    int dr= 0;
    private ProgressDialog progressDialog;
    private int position = 0;
    public static  Long time_in_milli_to_store = 0l;

    //Database
    JapDatabaseHandler db = new JapDatabaseHandler(this);
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_textView_store = findViewById(R.id.time_text);
        timer_text = findViewById(R.id.timer_textView);
        startJap = findViewById(R.id.startJap);
        stopJap = findViewById(R.id.stopJap);
        display_time_selected = findViewById(R.id.display_selected_time);
        options_spinner = findViewById(R.id.options);
        meditationActivity = findViewById(R.id.nextActivity);
        //Meditation activity intent
        meditationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,MeditationActivity.class);
                startActivity(in);
            }
        });

        //Variables initialisation from video Activity
       // timerTextView = findViewById(R.id.Jtimer);
        videoView = findViewById(R.id.videoViewV);

        timeInMilliTextView = findViewById(R.id.time_in_milli);

      /*  final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);*/


     //   videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoView.requestFocus();
        videoView.canPause();
        progressDialog = new ProgressDialog(MainActivity.this);
      /*  progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Video");
        progressDialog.setMessage("Please Hold on");
        progressDialog.setCancelable(false);
        progressDialog.show();*/

         //Spinner items array
        final ArrayAdapter<CharSequence> optionsAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.japOptions,android.R.layout.simple_spinner_item);
        optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options_spinner.setAdapter(optionsAdapter);

        builder = new AlertDialog.Builder(this);

        //shared preferences for time
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //shared preferences for option selected
        optionSelectedPreference = getSharedPreferences(OPTION_PREFERENCE,Context.MODE_PRIVATE);

        //item click listener on option spinner
        options_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
                optionEditor = optionSelectedPreference.edit();
                optionEditor.putString(selected_item,item);
                optionEditor.apply();
             //   Toast.makeText(MainActivity.this,"You selected "+item,Toast.LENGTH_SHORT).show();
                final String[] time = {"5","10","15","20","25","30"};

                //item click if statements
                // if statement if user clicks option by Time
                if(item.equalsIgnoreCase("by Time")){
                    videoUrl = null;
                  //  Toast.makeText(MainActivity.this,"by Time Clicked"+item,Toast.LENGTH_SHORT).show();
                    builder.setItems(time, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            time_in_minutes = Long.parseLong(time[which]);
                            display_time_selected.setVisibility(View.VISIBLE);
                            display_time_selected.setText((time_in_minutes).toString()+" Minutes ");
                            time_in_milli = time_in_minutes*60000;
                            Log.d(TAG, "onClick: Time selected :" + time_in_milli + " milliseconds");
                            editor = sharedPreferences.edit();
                            editor.putLong(Time_in_minutes,time_in_milli);
                            editor.apply();

                        }

                    });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.setTitle("Choose Time for Jap (in Minutes)");
                   alertDialog.show();


                }
                else if(item.equalsIgnoreCase("by Mala")){

                }
                else if(item.equalsIgnoreCase("with Pujya Gurudev")){

                }
                else if(item.equalsIgnoreCase("with Pujya Mataji")){

                }
                else{
                    display_time_selected.setVisibility(View.INVISIBLE);
                }
                db.addJapData(new JapData(0,item,time_in_minutes,0,null));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        startJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelectedPreference = getSharedPreferences(OPTION_PREFERENCE,Context.MODE_PRIVATE);
                selectedItemFromOptions = optionSelectedPreference.getString(selected_item,"Choose Options");
                Log.d(TAG, "onClick: Selected Item "+ selectedItemFromOptions);

               if(selectedItemFromOptions.equalsIgnoreCase("by Time")){
                   timer_text.setVisibility(View.VISIBLE);
                   sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                   Long timer_time = sharedPreferences.getLong(Time_in_minutes,10);
                   myCountdownTimer = new MyCountdownTimer(timer_time,100);
                   myCountdownTimer.start();

               }

               else if(selectedItemFromOptions.equalsIgnoreCase("with Pujya Gurudev")){
                   display_time_selected.setVisibility(View.INVISIBLE);
                   timer_text.setVisibility(View.VISIBLE);
                   videoView.setVisibility(View.VISIBLE);

                   progressDialog.setTitle("Loading Video");
                   progressDialog.setMessage("Please Hold on");
                   progressDialog.setCancelable(false);
                   progressDialog.show();
                  // Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                  // startActivity(intent);
                   videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                       @Override
                       public void onCompletion(MediaPlayer mp) {
                           Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();

                       }
                   });

                   videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                       @Override
                       public void onPrepared(MediaPlayer mp) {
                           progressDialog.dismiss();
                           dr = mp.getDuration();
                           int duration = mp.getDuration() / 1000;
                           int hours = duration / 3600;
                           int minutes = (duration / 60) - (hours * 60);
                           int seconds = duration - (hours * 3600) - (minutes * 60);
                           int videoWidth = videoView.getLayoutParams().width;
                           int videoHeight = videoView.getLayoutParams().height;
                           Log.d(TAG, "onPrepared: video Width "+videoWidth);
                           Log.d(TAG, "onPrepared: video Height "+videoHeight);

                           String formatted = String.format("%d:%02d:%02d", hours, minutes, seconds);
                           Toast.makeText(getApplicationContext(), "duration is " + formatted, Toast.LENGTH_LONG).show();
                           if (position == 0) {
                               videoView.start();
                               myCountdownTimer = new MyCountdownTimer(dr, 100);
                               myCountdownTimer.start();

                           }
                       }
                   });

                   videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                       @Override
                       public boolean onError(MediaPlayer mp, int what, int extra) {
                           Log.d(TAG, "onError: \"API123\", \"What \" + what + \" extra \" + extra");
                           return false;
                       }
                   });
                   videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                       @Override
                       public boolean onInfo(MediaPlayer mp, int what, int extra) {
                          /* switch (what) {
                               case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                   Log.d(TAG, "onInfo: "+timerTextView.getText());
                                   Log.d(TAG, "onInfo: "+timeInMilliTextView.getText());
                                   //time_in_milli_to_store = Long.parseLong((String) timeInMilliTextView.getText());
                                  // myCountdownTimer.cancel();
                                   //   case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                   //       myCountdownTimer.start();
                               case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                  // myCountdownTimer.cancel();
                                 //  myNewCountdownTimer = new MyCountdownTimer(time_in_milli_to_store,100);
                                  // myNewCountdownTimer.onTick(time_in_milli_to_store);
                                  // myNewCountdownTimer.start();

                           }*/

                           return false;
                       }

                   });


               }
               else if(selectedItemFromOptions.equalsIgnoreCase("with Pujya Mataji")){
                   display_time_selected.setVisibility(View.INVISIBLE);
                   timer_text.setVisibility(View.INVISIBLE);
                   videoView.setVisibility(View.VISIBLE);
                   //Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                   //startActivity(intent);
               }

            }
        });
        stopJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_time_selected.setVisibility(View.INVISIBLE);
                videoView.stopPlayback();
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