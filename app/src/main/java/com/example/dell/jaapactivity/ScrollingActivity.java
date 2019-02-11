package com.example.dell.jaapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ScrollingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      final ImageView japActivity = findViewById(R.id.japButton2);
        final ImageView  medActivity = findViewById(R.id.medButton2);
        final ImageView  swaActivity = findViewById(R.id.swaButton2);
        final ImageView   yagyaActivity = findViewById(R.id.yagButton2);

        japActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScrollingActivity.this,JapActivity.class);
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity.this,
                                japActivity,   // Starting view
                                ViewCompat.getTransitionName(japActivity)     // The String
                        );
                startActivity(i,options.toBundle());
            }
        });

        medActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScrollingActivity.this, MeditationActivity.class);
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity.this,
                                medActivity,   // Starting view
                                ViewCompat.getTransitionName(medActivity)     // The String
                        );
                startActivity(i,options.toBundle());
            }
        });
        swaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScrollingActivity.this,Swadhyay.class);
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity.this,
                                swaActivity,   // Starting view
                                ViewCompat.getTransitionName(swaActivity)    // The String
                        );
                startActivity(i,options.toBundle());
            }
        });
        yagyaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScrollingActivity.this,YagyaActivity.class);
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity.this,
                                yagyaActivity,   // Starting view
                                 ViewCompat.getTransitionName(yagyaActivity)    // The String
                        );
                startActivity(i,options.toBundle());
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
