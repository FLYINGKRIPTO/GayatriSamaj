package com.example.dell.jaapactivity;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlaceAPI extends AsyncTask{
    public static final String TAG = "PiyushTag";
    public static final String Type_Autocomplete="/autocomplete";
    public static final String Out_JSON = "/json";
    public static final String API_KEY ="AIzaSyAO-5VL6lMJ8G3GoTjqn5W8kZ0JqmdpgM4";
    public static final String meraSearch="gayatri";
    public static final String locality = "BHOPAL";
    public static final String base_url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
        //location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=YOUR_API_KEY";
        //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=23.2345089,77.4332221&keyword=shakti%20vidhyapeeth%20BHOPAL&key=AIzaSyAO-5VL6lMJ8G3GoTjqn5W8kZ0JqmdpgM4
        public static final String temp_base_url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=gayatri&location=23.2345089,77.4332221&radius=10000&type=hindu_temple&key=AIzaSyAO-5VL6lMJ8G3GoTjqn5W8kZ0JqmdpgM4";

    public Location mLocation;
    HttpURLConnection conn;
    ArrayList<parsedData> RawData;
    resultReceiverClass RRCobj;

    public void setmLocation(Location location){
        mLocation = location;
        Log.i(TAG,"resultReceiver.......Location set");
//        ArrayList<String> finalArray = jagahDhund();
    }

//    public ArrayList<String> jagahDhund(){

//    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("RawDataAddressList", RawData);
        RRCobj = new resultReceiverClass(new Handler());
        RRCobj.send(102,bundle);

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.i(TAG, "resultReceiver.....jagahDhund.......START with Location : " + mLocation.getLatitude() + "," + mLocation.getLongitude());
        HttpURLConnection conn = null;
        ArrayList<String> resultArray = null;
        String temp = "";
        URL url = null;
        try {
            url = new URL(temp_base_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            for(String line;(line = br.readLine())!=null;){
                temp=temp+line;
                temp=temp+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //raw Data build
        //Log.i(TAG,"TmpData"+temp);


        //StringBuilder jsonresult = new StringBuilder();
        //String temp=dummyData.dummy;
        RawData = getListRawData(temp);
        return null;
    }
    public ArrayList<parsedData> getListRawData(String temp) {
        ArrayList<parsedData> finalData = null;
        try {
            JSONObject jsonOBJ = new JSONObject(temp);
            JSONArray results = jsonOBJ.getJSONArray("results");
            finalData = new ArrayList<parsedData>(results.length());

            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                String tempAdd = result.getString("formatted_address");
                String tempID = result.getString("id");
                String tempName = result.getString("name");
                parsedData PDobj = new parsedData(tempAdd, tempID, tempName);
                finalData.add(PDobj);
               // Log.i(TAG, i + " out of " + results.length() + " : Name : " + tempName);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalData;
    }
}

        /*        try{
            //String sb = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key="+API_KEY;
            //StringBuilder sb = new StringBuilder(base_url+meraSearch+"&location="+mLocation.getLatitude()+","+mLocation.getLatitude()+"&radius=10000&key="+API_KEY);


            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?query=gayatri&location="+mLocation.getLatitude()+","+mLocation.getLongitude()+"&type=hindu_temple&radius=10000&key=AIzaSyAO-5VL6lMJ8G3GoTjqn5W8kZ0JqmdpgM4");
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            for(String line;(line = br.readLine())!=null;){
                jsonresult.append(line).append('\n');
            }
            Log.i(TAG,jsonresult.toString());

            JSONObject jsonObj = new JSONObject(jsonresult.toString());
            JSONArray arraywala = jsonObj.getJSONArray("results");
*/


/*            File file=null;
            file=File.createTempFile("PiyushFile",".tmp",null);
            file.setWritable(true);
            file.setReadable(true);
            String filename = file.getName();
            Log.i(TAG,"filename"+filename);
            int read;
            while
*/


         //   FileOutputStream fOutStream = new FileOutputStream(filename);
         //   fOutStream.write();


            /*            InputStream is = new URL(sb.toString()).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is),10);
            while((temp=br.readLine())!=null){
                jsonresult.append(temp).append('\n');
            }
            is.close();

            //InputStream in = conn.getInputStream();

*/


            /*int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }*/
//---------------
/*        } catch (MalformedURLException e){
            Log.i(TAG,"Cant Connect to google API");
            return resultArray;
        } catch(IOException e){
            Log.i(TAG,"Error processing API");
            return resultArray;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
*/
//----------------------------
  //      JsonParser jsonParser = new JsonParser();
      //  JSONObject jsonObject = jsonParser.



