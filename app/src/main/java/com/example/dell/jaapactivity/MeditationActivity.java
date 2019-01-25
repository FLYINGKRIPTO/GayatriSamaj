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

import java.util.List;

public class MeditationActivity extends AppCompatActivity {

    Button playButton;
    Button pauseButton;
    Button stopButton;
    TextView soundTrackName;
    int playButtonPressCount = 0;
    int buttonCountOnDestroy = 0;
    int pausePosition = 0;
    String soundTrack;
    boolean pauseIsPressed = false;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "MeditationActivity";
    SharedPreferences medSharedPreferences;
    SharedPreferences.Editor medEditor;
    public static final String BUTTON_COUNT_PREFERENCE = "CountPref" ;
    public static final String PLAY_BUTTON_CLICKS = "0";
    MeditationDataBaseHandler mDb= new MeditationDataBaseHandler(this);

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
        playButtonPressCount=buttonCountOnDestroy;
        Log.d(TAG, "onCreate: button Clicks : "+buttonCountOnDestroy);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                  long inserted=  mDb.addMeditationData(new MeditationData("No 1 Atam Bodh Dhyaan",mediaPlayer.getDuration(),1));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                long inserted =     mDb.addMeditationData(new MeditationData("No 2 Panchkosh Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted : "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                  long inserted =   mDb.addMeditationData(new MeditationData("No 3 Sharir Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                   long inserted =  mDb.addMeditationData(new MeditationData("No 4 Amrit Varsha Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted: "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                    long inserted = mDb.addMeditationData(new MeditationData("No 5  jyoti Avardham Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted" + inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                long inserted =     mDb.addMeditationData(new MeditationData("No 6 Naad yog Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                                mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
                    long inserted = mDb.addMeditationData(new MeditationData("No 7 Tatva Bodh Dhyaan",mediaPlayer.getDuration(),2));
                    Log.d(TAG, "onClick: inserted : "+ inserted);
                    List<MeditationData> meditationDataList = mDb.getAllMeditationData();
                    for (MeditationData mp : meditationDataList) {
                        String log = "Id: " + mp.getId() + " ,Audiio Nsme : " + mp.getAudioName() + " ,Duration Time: " +
                         mp.getDuration();
                        // Writing Contacts to log
                        Log.d("Name: ", log);

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
