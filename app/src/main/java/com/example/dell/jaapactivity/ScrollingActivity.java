package com.example.dell.jaapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

import java.util.ArrayList;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        fragmentManager = getSupportFragmentManager();
        initMenuFragment();
        final ImageView japActivity = findViewById(R.id.japButton22);
        final ImageView  medActivity = findViewById(R.id.medButton22);
        final ImageView  swaActivity = findViewById(R.id.swaButton22);
        final ImageView   yagyaActivity = findViewById(R.id.yagButton22);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_scrolling);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_scrolling);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        YoYo.with(Techniques.FadeInUp).duration(2000).playOn(japActivity);
        YoYo.with(Techniques.FadeInUp).duration(1800).playOn(medActivity);
        YoYo.with(Techniques.FadeInUp).duration(1800).playOn(swaActivity);
        YoYo.with(Techniques.FadeInUp).duration(1800).playOn(yagyaActivity);


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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_reports) {
            // Handle the camera action
            Intent reportsIntet = new Intent(ScrollingActivity.this,ReportActivity.class);
            startActivity(reportsIntet);
        } else if (id == R.id.jap_reports) {

        } else if (id == R.id.meditation_reports) {

        } else if (id == R.id.swadhyay_reports) {

        } else if (id == R.id.yagya_reports) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
      menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);

    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject("Close");
        close.setResourceValue(R.drawable.ic_close_black_24dp);
        MenuObject send = new MenuObject("Send message");
        send.setResourceValue(R.drawable.ic_send_black_24dp);
        MenuObject like = new MenuObject("Reports");
        like.setResourceValue(R.drawable.analytics);
        MenuObject addFr = new MenuObject("Account");
        addFr.setResourceValue(R.drawable.ic_account_circle_black_24dp);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        return menuObjects;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null ){
                    mMenuDialogFragment.show(fragmentManager,"ContextMenuDialogFragment");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
