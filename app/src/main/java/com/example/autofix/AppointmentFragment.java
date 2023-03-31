package com.example.autofix;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppointmentFragment extends Fragment {

    DatabaseHelper databaseHelper;
    FragCommunicator fragCommunicator;
    int serviceID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        TextView date = view.findViewById(R.id.txtAppDate);
        TextView service = view.findViewById(R.id.txtAppService);
        TextView appType = view.findViewById(R.id.txtAppType);
        TextView provider = view.findViewById(R.id.txtAppProvider);
        TextView city = view.findViewById(R.id.txtProvCity);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userID = sharedPreferences.getInt("USER_ID",0);
        int providerID = sharedPreferences.getInt("PROVIDER_ID",0);
        String str = sharedPreferences.getString("USERTYPE","");
        if(str.equals("Customer")){

            Cursor cursor = databaseHelper.nextCustomerAppointment(userID);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()) {
                    date.setText(cursor.getString(3) + "\n" +cursor.getString(4));
                    service.setText(cursor.getString(0));
                    appType.setText(cursor.getString(5));
                    provider.setText(cursor.getString(1));
                    city.setText(cursor.getString(2));
                    serviceID = cursor.getInt(6);
                }

            }
            else{
                date.setText("No upcoming appointments");
                service.setText("");
                appType.setText("");
                provider.setText("");
                city.setText("");
            }
            cursor.close();
        }
        if(str.equals("Provider")){
            Cursor cursor = databaseHelper.selectProviderAppointments(providerID);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    date.setText(cursor.getString(3) + "\n" + cursor.getString(4));
                    service.setText(cursor.getString(0));
                    appType.setText(cursor.getString(5));
                    provider.setText(cursor.getString(1));
                    city.setText(cursor.getString(2));
                    serviceID = cursor.getInt(6);
                }
            }
            else{
                date.setText("No upcoming appointments");
                service.setText("");
                appType.setText("");
                provider.setText("");
                city.setText("");
            }

            cursor.close();

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;

    }


    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view,savedInstance);
        sendValue(view);
//        fragCommunicator = (FragCommunicator) getActivity();
//
//        fragCommunicator.sendData((serviceID));

    }

    public void sendValue(View view){
        Bundle bundle = new Bundle();
        bundle.putInt("SERVICE_ID", serviceID);
        getParentFragmentManager().setFragmentResult("SERVICE_ID", bundle);
    }

}