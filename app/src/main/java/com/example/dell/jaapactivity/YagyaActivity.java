package com.example.dell.jaapactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class YagyaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yagya);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_scrolling);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_scrolling);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.all_reports) {
            // Handle the camera action
            Intent reportsIntet = new Intent(YagyaActivity.this,ReportActivity.class);
            startActivity(reportsIntet);
        }
        if(id == R.id.japInMenu){
            Intent japIntent = new Intent(YagyaActivity.this,JapActivity.class);
            startActivity(japIntent);

        }
        if(id == R.id.meditationInMenu){
            Intent medIntent = new Intent(YagyaActivity.this,MeditationActivity.class);
            startActivity(medIntent);

        }
        if(id== R.id.swadhyayInMenu){
            Intent intent = new Intent(YagyaActivity.this,Swadhyay.class);
            startActivity(intent);
        }
        if(id == R.id.yagyaInMenu){
            Intent intent  = new Intent(YagyaActivity.this,YagyaActivity.class);
            startActivity(intent);
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
