package com.example.dell.jaapactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MyDatePicketFragment extends DialogFragment {
    public int selected_date;
    public int selected_month;
    public int selected_year;
    private static final String TAG = "MyDatePicketFragment";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year =  c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);



        return new DatePickerDialog(getActivity(),dateSetListener,year,month,day);
    }
    public DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Toast.makeText(getActivity(),"Selected date is "+ view.getYear() +

                            "/"+ (view.getMonth())+
                            "/"+ view.getDayOfMonth(),Toast.LENGTH_SHORT).show();

                    selected_date = view.getDayOfMonth();
                    selected_month = view.getMonth();
                    selected_year = view.getYear();
                    Log.d(TAG, "onCreate: selected date from date picker "+ selected_date);
                    Log.d(TAG, "onCreate: selected month from date picker "+ selected_month);
                    Log.d(TAG, "onCreate: selected year from date picker "+ selected_year);


                }
            };


}
