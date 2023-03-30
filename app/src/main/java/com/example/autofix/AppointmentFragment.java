package com.example.autofix;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AppointmentFragment extends Fragment {

    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(this.getContext());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        int userID = sharedPreferences.getInt("USER_ID",0);
        Cursor cursor = databaseHelper.selectCustomerAppointments(userID);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){

            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }
}