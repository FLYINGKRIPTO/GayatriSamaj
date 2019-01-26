package com.example.dell.jaapactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.jaapactivity.Meditation.MeditationData;
import com.example.dell.jaapactivity.Meditation.MeditationDataBaseHandler;
import com.example.dell.jaapactivity.ReportManager.ReportData;
import com.example.dell.jaapactivity.ReportManager.ReportDataBaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeditationActivity extends AppCompatActivity {

    Button playButton;
    Button pauseButton;
    Button stopButton;
    TextView soundTrackName;
    int playButtonPressCount = 0;
    int buttonCountOnDestroy = 0;
    int pausePosition = 0;
    int id = 0;
    String soundTrack;
    boolean pauseIsPressed = false;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "MeditationActivity";
    SharedPreferences medSharedPreferences;
    SharedPreferences.Editor medEditor;
    public static final String BUTTON_COUNT_PREFERENCE = "CountPref" ;
    public static final String PLAY_BUTTON_CLICKS = "0";
    //Mediatation database
    MeditationDataBaseHandler mDb= new MeditationDataBaseHandler(this);

    //Report data base
    ReportDataBaseHandler rDb = new ReportDataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

     //   mediaPlayer = MediaPlayer.create(this,R.raw.day_one);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        soundTrackName = findViewById(R.id.soundTrackName);
        medSharedPreferences = getSharedPreferences(BUTTON_COUNT_PREFERENCE, Context.MODE_PRIVATE);
        medSharedPreferences = getSharedPreferences(BUTTON_COUNT_PREFERENCE, Context.MODE_PRIVATE);
        buttonCountOnDestroy = medSharedPreferences.getInt(PLAY_BUTTON_CLICKS,0);
        if(buttonCountOnDestroy==7){
            buttonCountOnDestroy=0;
        }

        playButtonPressCount=buttonCountOnDestroy;
        Log.d(TAG, "onCreate: button Clicks : "+buttonCountOnDestroy);

        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(currentTime);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        final String formattedTime = timeFormat.format(currentTime);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        final String formattedDay = dayFormat.format(currentTime);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                playButton.setEnabled(false);
                playButtonPressCount++;
                medSharedPreferences = getSharedPreferences(BUTTON_COUNT_PREFERENCE, Context.MODE_PRIVATE);
                medEditor = medSharedPreferences.edit();
                medEditor.putInt(PLAY_BUTTON_CLICKS,playButtonPressCount);
                medEditor.apply();
                Log.d(TAG,"onClick: "+playButtonPressCount);
                if(playButtonPressCount==1){
                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_one);
                    if(pauseIsPressed){
                           mediaPlayer.seekTo(pausePosition+100);
                    }

                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                    soundTrackName.setText("No 1 Atam Bodh Dhyaan");

                    //meditation database
                  long inserted=  mDb.addMeditationData(new MeditationData("No 1 Atam Bodh Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);



                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Atam Bodh Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }

                else if(playButtonPressCount==2){

                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_two);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition+100);
                    }
                    soundTrackName.setText("No 2 Panchkosh Dhyaan");
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                long inserted =     mDb.addMeditationData(new MeditationData("No 2 Panchkosh Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted : "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);


                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Panchkosh Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }



                }
                else if(playButtonPressCount==3){
                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_three);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition=100);
                    }
                    soundTrackName.setText("No 3 Sharir Dhyaan");
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                  long inserted =   mDb.addMeditationData(new MeditationData("No 3 Sharir Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Sharir Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }
                else if(playButtonPressCount==4){

                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_four);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition+100);
                    }
                    soundTrackName.setText("No 4 Amrit Varsha Dhyaan");
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                   long inserted =  mDb.addMeditationData(new MeditationData("No 4 Amrit Varsha Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted: "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Amrit Varsha Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }
                else if(playButtonPressCount==5){

                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_five);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition+100);
                    }
                    soundTrackName.setText("No 5 Jyoti Avdhrnam Dhyaan");
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                    long inserted = mDb.addMeditationData(new MeditationData("No 5  jyoti Avardham Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted" + inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Jyoti Avardhanam Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }
                else if(playButtonPressCount==6){
                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_six);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition+100);
                    }
                    mediaPlayer.setLooping(true);
                    soundTrackName.setText("No 6 Naad yog Dhyaan ");
                    mediaPlayer.start();
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                long inserted =     mDb.addMeditationData(new MeditationData("No 6 Naad yog Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Naad Yog Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }
                else if(playButtonPressCount==7){

                    mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.day_seven);
                    if(pauseIsPressed){
                        mediaPlayer.seekTo(pausePosition+100);
                    }
                    mediaPlayer.setLooping(true);
                    soundTrackName.setText("No 7 Tatv Bodh Dhyaan");
                    playButtonPressCount=0;
                    mediaPlayer.start();
                    Log.d(TAG, "onClick: Position " + mediaPlayer.getCurrentPosition());
                    long inserted = mDb.addMeditationData(new MeditationData("No 7 Tatva Bodh Dhyaan",mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: inserted : "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audio Name : " + mp.getAudioName() + " ,Duration Time: " +
                         mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

                    }
                    long reportinserted = rDb.addUserReportData(new ReportData("Meditation",formattedDate,formattedTime,formattedDay,"Tatv Bodh Dhyaan ",mediaPlayer.getDuration(),mediaPlayer.getDuration()));
                    Log.d(TAG, "onClick: report inserted : "+reportinserted);
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
                                + ", Audio Name : "+rp.getAudioName(); //8
                        Log.d("Report: ",reportLog);
                    }

                }

            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setEnabled(true);
                Log.d(TAG, "onClick: Pause Button Clicked");
                playButtonPressCount--;
                Log.d(TAG, "onClick: "+  mediaPlayer.getCurrentPosition());
                pausePosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
                pauseIsPressed = true;
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setEnabled(true);
                pauseIsPressed = false;
                pausePosition = 0;
                Log.d(TAG, "onClick: stop button clicked "+mediaPlayer.getCurrentPosition()/1000 +" seconds" );

                mediaPlayer.stop();

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Method Called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Method called");

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: Method called");
        medSharedPreferences = getSharedPreferences(BUTTON_COUNT_PREFERENCE, Context.MODE_PRIVATE);
        buttonCountOnDestroy = medSharedPreferences.getInt(PLAY_BUTTON_CLICKS,0);
        Log.d(TAG, "onDestroy: "+buttonCountOnDestroy);
        super.onDestroy();
    }


}
