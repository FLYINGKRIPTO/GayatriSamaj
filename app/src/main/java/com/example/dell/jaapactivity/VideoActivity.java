package com.example.dell.jaapactivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.concurrent.TimeUnit;

public class VideoActivity extends AppCompatActivity {
    TextView timerTextView;
    VideoView videoView;
    TextView timeInMilliTextView;
    MyCountdownTimer myCountdownTimer;
    MyCountdownTimer myNewCountdownTimer;
    CountDownTimer countDownTimer;
    int videoTime = 0;
    int dr= 0;
    boolean videoState = true;
    private ProgressDialog progressDialog;
    private int position = 0;
    SharedPreferences shpre;
    SharedPreferences.Editor shpreEditor;
    private static final String TAG = "VideoActivity";
    public static final String PREFERENCE = "MyPreference";
    public static final String Time_in_minutes = "timeKey";
    public static  Long time_in_milli_to_store = 0l;


    //Vector<YouTubeVideos> youTubeVideos= new Vector<YouTubeVideos>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        timerTextView = findViewById(R.id.Jtimer);
        videoView = findViewById(R.id.videoViewV);
        timeInMilliTextView = findViewById(R.id.time_in_milli);

        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);


        videoView.setMediaController(mediaController);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Video");
        progressDialog.setMessage("Please Hold on");
        progressDialog.setCancelable(false);
        progressDialog.show();



        // youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"http://www.ebookfrenzy.com/android_book/movie.mp4\" frameborder=\"0\" allowfullscreen></iframe>",2222000l) );
        //    youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7yjXVQCsGQ4\" frameborder=\"0\" allowfullscreen></iframe>",2511000l) );
        //    youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9P2DwodBdQM\" frameborder=\"0\" allowfullscreen></iframe>",2611220l) );

        // VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);
        // recyclerView.setAdapter(videoAdapter);
        videoView.setVideoURI(Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoView.requestFocus();
        videoView.canPause();

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
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Log.d(TAG, "onInfo: "+timerTextView.getText());
                        Log.d(TAG, "onInfo: "+timeInMilliTextView.getText());
                        time_in_milli_to_store = Long.parseLong((String) timeInMilliTextView.getText());
                        myCountdownTimer.cancel();
                        //   case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //       myCountdownTimer.start();
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        myCountdownTimer.cancel();
                        myNewCountdownTimer = new MyCountdownTimer(time_in_milli_to_store,100);
                        myNewCountdownTimer.onTick(time_in_milli_to_store);
                        myNewCountdownTimer.start();

                }

                return false;
            }
        });

    }

    public class MyCountdownTimer extends CountDownTimer{


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountdownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            timerTextView.setText(hms);
            timeInMilliTextView.setText( String.valueOf(millisUntilFinished));
        }

        @Override
        public void onFinish() {


        }







    }


    }


