package com.example.dell.jaapactivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "PiyushTag";
    static ProgressBar progressBar;
    static NetworkInfo nInfo;
    static boolean GPSflag=false,INTERNETflag=false;




    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED) {
            //check for network and gps
            checkMeraGPS();
            checkInternetConnectivity();
            if(GPSflag&&INTERNETflag){
                moveOn();
            }
        }else{
            Log.i(TAG,"Permission not available......exiting Application");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(MainActivity.this).setMessage("Permissiion Rejected....exiting Applicaiton").create().show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }, 2000);
        }
    }

    public void moveOn(){
        final Intent i4= new Intent(MainActivity.this,LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i4);
                finish();
            }
        }, 3000);


    }


    public void checkMeraGPS(){
        LocationManager locationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            alertMessageNoGps();

        }else{
            GPSflag=true;
            if(GPSflag&&INTERNETflag){
                moveOn();
            }
        }

    }
    public void alertMessageNoGps(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS isdisabled, wanna Enable....???")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        if(GPSflag&&INTERNETflag){
                            moveOn();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        new AlertDialog.Builder(getBaseContext()).setMessage("NO GPS exiting application...").create().show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void checkInternetConnectivity(){
        ConnectivityManager cnManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        nInfo = cnManager.getActiveNetworkInfo();
        if(nInfo!=null&&nInfo.isConnected()){
            Toast.makeText(this, "isConnected", Toast.LENGTH_SHORT).show();
            INTERNETflag=true;
            if(GPSflag&&INTERNETflag){
                moveOn();
            }
        }else{
            Toast.makeText(this, "isNotConnected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1234);


        }else{
            checkMeraGPS();
            checkInternetConnectivity();
            if(GPSflag&&INTERNETflag){
                moveOn();
            }

        }
        new CountDownTimer(20000,2000){
            @Override
            public void onTick(long millisUntilFinished) {
                if(GPSflag&&INTERNETflag){
                    moveOn();
                }
            }

            @Override
            public void onFinish() {
                new AlertDialog.Builder(getBaseContext()).setMessage("NO GPS exiting application...").create().show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GPSflag&&INTERNETflag){
            moveOn();
        }
    }
}


//--------------------Internet Check----------------------------------------------------------------//





/*                        progressDialog.setTitle("Checking Internet Connectivity");
                        progressDialog.setCancelable(false);
                        progressDialog.setCanceledOnTouchOutside(false);
*/

                        /*ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
                        if(null!=activeNetwork){
                            Log.i(TAG,"Network Connected");
                        }else{

                        }*/

//--------------------Internet Check----------------------------------------------------------------//
