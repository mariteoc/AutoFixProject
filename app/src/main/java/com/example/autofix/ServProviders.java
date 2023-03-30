package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServProviders extends AppCompatActivity implements RecyclerViewInt{

    DatabaseHelper databaseHelper;
    ArrayList<ProvidersModel> providersModels = new ArrayList<>();
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_providers);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        databaseHelper= new DatabaseHelper(this);
        setTitle("Service Providers");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setUpProvidersModels();
        Prov_CustomAdapter adapter = new Prov_CustomAdapter(this,providersModels,this);
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

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
       if(item.getItemId() == R.id.menu){
           startActivity(new Intent(ServProviders.this, CustomerMenu.class));
       }
       return true;
    }

    private void setUpProvidersModels() {

        String provID;
        String provName;
        String provPhone;
        String provCity;

        HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String, String>>();
        int counter = 0;

        String name;
        String phone;
        String city;
        int id;

        ArrayList<String> providerID = new ArrayList<>();
        ArrayList<String> providerNames = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();
        ArrayList<String> cities = new ArrayList<>();

        Cursor cursor = databaseHelper.selectAllProviders();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                provID = cursor.getString(0);
                provName = cursor.getString(1);
                provPhone = cursor.getString(2);
                provCity = cursor.getString(3);
                myMap.put("key" + counter, new HashMap<String, String>());
                myMap.get("key" + counter).put("provID", provID);
                myMap.get("key" + counter).put("provName", provName);
                myMap.get("key" + counter).put("phone", provPhone);
                myMap.get("key" + counter).put("city", provCity);
                counter++;
            }

            for (int i = 0; i < myMap.size(); i++) {
                id = Integer.parseInt(myMap.get("key" + i).get("provID"));
                providerID.add(String.valueOf(id));
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
                providersModels.add(new ProvidersModel(providerID.get(j),providerNames.get(j),
                        cities.get(j),phones.get(j)));
            }

        }

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ServProviders.this, Services.class);
        editor.putInt("PROV_ID", providersModels.get(position).getProvID());
        editor.apply();
        startActivity(intent);

    }
}