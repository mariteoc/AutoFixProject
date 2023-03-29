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

import java.util.ArrayList;
import java.util.HashMap;

public class All_Users extends AppCompatActivity implements RecyclerViewInt {

    DatabaseHelper databaseHelper;
    ArrayList<UsersModel> usersModels = new ArrayList<>();
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        databaseHelper= new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recView);
        setUpUsersModels();
        User_CustomAdapter adapter = new User_CustomAdapter(this,usersModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.searchUsers);

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
//        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.add:
                editor.putString("ACTION", "ADD");
                editor.putString("USERTYPE","Customer");
                editor.commit();
                startActivity(new Intent(All_Users.this, IndividualUser.class));
                return true;
            case R.id.menu:
                startActivity(new Intent(All_Users.this, ProviderMenu.class));
                return true;
            default:
                return false;
        }

    }

    private void setUpUsersModels() {

        String uName;
        String uEmail;
        String uType;
        int userID;


        HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String, String>>();
        int counter = 0;

        String username;
        String email;
        String userType;
        int id;


        ArrayList<Integer> userIDs = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> userTypes = new ArrayList<>();

        Cursor cursor = databaseHelper.selectAllCustomerUsers();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                id = cursor.getInt(0);
                username = cursor.getString(1);
                email = cursor.getString(4);
                userType = cursor.getString(8);
                myMap.put("key" + counter, new HashMap<String, String>());
                myMap.get("key" + counter).put("userID", String.valueOf(id));
                myMap.get("key" + counter).put("username", username);
                myMap.get("key" + counter).put("email", email);
                myMap.get("key" + counter).put("userType", userType);
                counter++;
            }

            for (int i = 0; i < myMap.size(); i++) {
                id = Integer.parseInt(myMap.get("key" + i).get("userID"));
                userIDs.add(id);
            }

            for (int i = 0; i < myMap.size(); i++) {
                username = myMap.get("key" + i).get("username");
                usernames.add(username);
            }
            for (int i = 0; i < myMap.size(); i++) {
                email = myMap.get("key" + i).get("email");
                emails.add(email);
            }

            for (int i = 0; i < myMap.size(); i++) {
                userType = myMap.get("key" + i).get("userType");
                userTypes.add(userType);
            }

            for(int j = 0;j<usernames.size();j++){
                usersModels.add(new UsersModel(usernames.get(j),
                        emails.get(j),userTypes.get(j),userIDs.get(j)));

            }

        }

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(All_Users.this, IndividualUser.class);
        editor.putInt("USER_ID", usersModels.get(position).getId());
        editor.putString("ACTION","EDIT");
        editor.apply();
        startActivity(intent);

    }
}