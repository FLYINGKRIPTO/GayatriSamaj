package com.example.dell.jaapactivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context b;
    ArrayList<parsedData> mDataArray;
    public static final String TAG="PiyushTag";

    public CustomAdapter(Context context, ArrayList<parsedData> DataArray){
        this.b=context;
        this.mDataArray=DataArray;
    }

    @Override
    public int getCount() {
        return mDataArray.size();
    }

    @Override
    public boolean isEmpty() {
        Log.i(TAG,"adapter is empty");
        return super.isEmpty();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG,"calling 001");
        parsedData temp_parsed_data = mDataArray.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(b);
        convertView = layoutInflater.inflate(R.layout.patta,null);
        Log.i(TAG,"View @ Position : "+position);
        TextView name_text = (TextView)convertView.findViewById(R.id.text_name);
        TextView add_text = (TextView)convertView.findViewById(R.id.add_name);
        Log.i(TAG,"View Lga Diye");
        name_text.setText(temp_parsed_data.getName());
        add_text.setText((temp_parsed_data.getAddress()));

        return convertView;
    }
}
