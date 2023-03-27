package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServProviders extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ArrayList<ProvidersModel> providersModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_providers);
        databaseHelper= new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setUpProvidersModels();
        Prov_CustomAdapter adapter = new Prov_CustomAdapter(this,providersModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private void setUpProvidersModels() {

        String provName;
        String provPhone;
        String provCity;

        HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String, String>>();
        int counter = 0;

        String name;
        String phone;
        String city;

        ArrayList<String> providerNames = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();
        ArrayList<String> cities = new ArrayList<>();

        Cursor cursor = databaseHelper.selectAllProviders();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                provName = cursor.getString(1);
                provPhone = cursor.getString(2);
                provCity = cursor.getString(3);
                myMap.put("key" + counter, new HashMap<String, String>());
                myMap.get("key" + counter).put("provName", provName);
                myMap.get("key" + counter).put("phone", provPhone);
                myMap.get("key" + counter).put("city", provCity);
                counter++;
            }

            for (int i = 0; i < myMap.size(); i++) {
                name = myMap.get("key" + i).get("provName");
                providerNames.add(name);
            }
            for (int i = 0; i < myMap.size(); i++) {
                phone = myMap.get("key" + i).get("phone");
                phones.add(phone);
            }

            for (int i = 0; i < myMap.size(); i++) {
                city = myMap.get("key" + i).get("city");
                cities.add(city);
            }

            for(int j = 0;j<providerNames.size();j++){
                providersModels.add(new ProvidersModel(providerNames.get(j),
                        cities.get(j),phones.get(j)));
            }

        }

    }


}