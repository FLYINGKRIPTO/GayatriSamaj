package com.example.dell.jaapactivity;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

public class resultReceiverClass extends ResultReceiver {

    Location myLocation;
    private final static String TAG = "PiyushTag";
    public resultReceiverClass(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        switch(resultCode){
            case 101:

                PlaceAPI mPlaceAPI = new PlaceAPI();
                myLocation = resultData.getParcelable("myLocation");
                if(myLocation==null){
                    Log.i(TAG,"resultReceiver....Location NULL");
                }else{
                    Log.i(TAG,"resultReceiver....Got Location");
                    mPlaceAPI.setmLocation(myLocation);
                    Log.i(TAG,"Location Bhej Di......PlaceAPI");
                    mPlaceAPI.execute();
                    Log.i(TAG,"highlight----------------------");
                }
                break;

            case 102:

                MainActivity mainActivity_temp_obj = new MainActivity();
                mainActivity_temp_obj.setLocationSpinnerRawData(resultData.<parsedData>getParcelableArrayList("RawDataAddressList"));

                break;

        }


        super.onReceiveResult(resultCode, resultData);
    }
}
