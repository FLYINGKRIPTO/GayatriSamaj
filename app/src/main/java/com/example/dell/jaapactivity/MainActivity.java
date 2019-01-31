package com.example.dell.jaapactivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.RequiresApi;

public class MainActivity extends AppCompatActivity {

    Location myLocation;
    public final static String TAG = "PiyushTag";
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    public TextView t1,dob;
    PlaceAPI mPlaceAPI;
    resultReceiverClass mRRCobj;
    static ArrayList<parsedData> locationSpinnerRawData;
    String flag="";
    int y,m,d;
    Dialog dialog_main_back;


    public void setLocationSpinnerRawData(ArrayList<parsedData> locationSpinnerRawData) {
        this.locationSpinnerRawData = locationSpinnerRawData;
        //flag="changed";

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            Log.i(TAG,"ononRequestPermissionsResult START");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG,"ononRequestPermissionsResult START....if part");

            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Log.i(TAG,"ononRequestPermissionsResult START..else part");

            }
        }else{
            Log.i(TAG,"ononRequestPermissionsResult START..not granted..!!");
        }
        Log.i(TAG,"ononRequestPermissionsResult END");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.choose_location);
        dob=(TextView)findViewById(R.id.edit_DOB);

        t1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Data check \n"+locationSpinnerRawData.get(0).getName());
                dialog_main_back = new Dialog(MainActivity.this);
                Log.i(TAG,"new Dialog");
                dialog_main_back.setContentView(R.layout.dialogue_for_list);
                Log.i(TAG,"setContentView");
                ListView main_list_view = (ListView)dialog_main_back.findViewById(R.id.list_view_main);
                Log.i(TAG,"listView");
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,locationSpinnerRawData);
                Log.i(TAG,"CustomAdapter");
                main_list_view.setAdapter(customAdapter);
                main_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        t1.setText("Temple Location : "+locationSpinnerRawData.get(position).getName()+"\n"+locationSpinnerRawData.get(position).getAddress());
                        dialog_main_back.dismiss();
                    }
                });
                Log.i(TAG,"setCustomAdapter");
                dialog_main_back.setCanceledOnTouchOutside(true);
                dialog_main_back.create();
                Log.i(TAG,"Dialog Create");
                dialog_main_back.show();
                Log.i(TAG,"Dialog Show");
            }
        });

        final Calendar cal = Calendar.getInstance();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText("Date of Birth : "+dayOfMonth+"/"+month+1+"/"+year);
                    }
                },y,m=+1,d);
                datePickerDialog.show();
            }
        });






//--------------------------------------------------------backendPart-----------------------------------------------------------

        myLocation=null;
        mRRCobj = new resultReceiverClass(new Handler());

        final int requestCodeToSendLocation=101;
        Log.i(TAG,"onCreate........Init");


        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1234);
        } else {
            //when permission granted
            //building locaitonRequest
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setSmallestDisplacement(10);
            //fusedLocationProvider
            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
            //building Location Call Back
            Log.i(TAG,"Location Callback Done");
            locationCallback = new LocationCallback() {
                public void onLocationResult(LocationResult locationResult) {
                    Log.i(TAG,"onLocationResult");
                    for (Location location : locationResult.getLocations()) {
                        myLocation=location;
                        if(location==null){
                            Log.i(TAG,"Dont have Location,......NULL Location");
                        }
                        else{
                            Log.i(TAG,"Got Location...."+location.getLatitude()+","+location.getLongitude());
                            //removeLocationUpdates;
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("myLocation",location);
                            mRRCobj.send(requestCodeToSendLocation,bundle);
                            Log.i(TAG,"Location Bhej DI....resultReceiver....!!");
                            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                        }

                        //Log.i(TAG,location.getLatitude() + "/" + location.getLongitude());
                    }
                }
            };
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1234);
                return;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        }


//------------------------------------------backendPart----------------------------------------------------------------------










    }
}
