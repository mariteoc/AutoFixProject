package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceHistory extends AppCompatActivity implements RecyclerViewInt {

    DatabaseHelper databaseHelper;
    ArrayList<AppointmentsModel> appointmentsModels = new ArrayList<>();
    SharedPreferences.Editor editor;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = sharedPreferences.getInt("USER_ID",0);
        editor = sharedPreferences.edit();
        databaseHelper= new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerApp);
        setUpAppointmentsModels();
        Appoint_CustomAdap adapter = new Appoint_CustomAdap(this,appointmentsModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){

                startActivity(new Intent(ServiceHistory.this, CustomerMenu.class));
                return false;
    }


    private void setUpAppointmentsModels() {
//
//        String appDate;
//        String appService;
//        String appProvider;


        HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String, String>>();
        int counter = 0;

        String date;
        String service;
        String provider;
        int id;


//        ArrayList<Integer> userIDs = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> services = new ArrayList<>();
        ArrayList<String> providers = new ArrayList<>();

        Cursor cursor = databaseHelper.selectCustomerAppointments(userID);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                date = cursor.getString(3);
                service = cursor.getString(0);
                provider = cursor.getString(1);
                myMap.put("key" + counter, new HashMap<String, String>());
                myMap.get("key" + counter).put("date", date);
                myMap.get("key" + counter).put("service", service);
                myMap.get("key" + counter).put("provider", provider);
                counter++;
            }
//
//            for (int i = 0; i < myMap.size(); i++) {
//                id = Integer.parseInt(myMap.get("key" + i).get("userID"));
//                userIDs.add(id);
//            }

            for (int i = 0; i < myMap.size(); i++) {
                date = myMap.get("key" + i).get("date");
                dates.add(date);
            }
            for (int i = 0; i < myMap.size(); i++) {
                service = myMap.get("key" + i).get("service");
                services.add(service);
            }

            for (int i = 0; i < myMap.size(); i++) {
                provider = myMap.get("key" + i).get("provider");
                providers.add(provider);
            }

            for(int j = 0;j<dates.size();j++){
                appointmentsModels.add(new AppointmentsModel(dates.get(j),
                        services.get(j),providers.get(j)));

            }

        }

    }


    @Override
    public void onItemClick(int position) {
//        Intent intent = new Intent(All_Users.this, IndividualUser.class);
//        editor.putInt("USER_ID", usersModels.get(position).getId());
//        editor.putString("ACTION","EDIT");
//        editor.apply();
//        startActivity(intent);

    }
}