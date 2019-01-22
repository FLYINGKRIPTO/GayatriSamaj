package com.example.dell.jaapactivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class VideoActivity extends AppCompatActivity  {
    TextView timerTextView;
    VideoView videoView;
    MyCountdownTimer myCountdownTimer;
    int videoTime = 0;
    private static final String TAG = "VideoActivity";
    Vector<YouTubeVideos> youTubeVideos= new Vector<YouTubeVideos>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        timerTextView = findViewById(R.id.Jtimer);
        videoView = findViewById(R.id.videoViewV);
        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);



       // youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"http://www.ebookfrenzy.com/android_book/movie.mp4\" frameborder=\"0\" allowfullscreen></iframe>",2222000l) );
    //    youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7yjXVQCsGQ4\" frameborder=\"0\" allowfullscreen></iframe>",2511000l) );
    //    youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9P2DwodBdQM\" frameborder=\"0\" allowfullscreen></iframe>",2611220l) );

       // VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);
       // recyclerView.setAdapter(videoAdapter);
        videoView.setVideoURI(Uri.parse("http://www.ebookfrenzy.com/android_book/movie.mp4"));
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();

            }
        });
      videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
          @Override
          public void onPrepared(MediaPlayer mp) {
              int dr = mp.getDuration();
              int duration=mp.getDuration()/1000;
              int hours = duration / 3600;
              int minutes = (duration / 60) - (hours * 60);
              int seconds = duration - (hours * 3600) - (minutes * 60) ;
              String formatted = String.format("%d:%02d:%02d", hours, minutes, seconds);
              Toast.makeText(getApplicationContext(), "duration is " + formatted ,  Toast.LENGTH_LONG).show();
              myCountdownTimer = new MyCountdownTimer(dr,100);
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
        public MyCountdownTimer(long millisInFuture, long countDownInterval) {
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


