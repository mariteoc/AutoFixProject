package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IndividualUser extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    String action;
    EditText fullName;
    EditText cell;
    EditText address;
    EditText email;
    EditText username;
    EditText pass;
    EditText city;
    String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_user);
        fullName = findViewById(R.id.editFullN);
        cell = findViewById(R.id.editCellNumb);
        address = findViewById(R.id.editUserAdd);
        email = findViewById(R.id.editEmail);
        username = findViewById(R.id.editUserN);
        pass = findViewById(R.id.editPassword);
        city = findViewById(R.id.editUserCity);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userType = sharedPreferences.getString("USERTYPE","");
        Button btnSave = findViewById(R.id.btnUserSave);
        databaseHelper = new DatabaseHelper(this);
        action = sharedPreferences.getString("ACTION","");


        if (action.equals("REG") || action.equals("EDIT")) {
            username.setEnabled(true);
            pass.setEnabled(true);
            fullName.setEnabled(true);
            cell.setEnabled(true);
            address.setEnabled(true);
            email.setEnabled(true);
            city.setEnabled(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {
                if (areEditTextsFilled()) {
                    isInserted = databaseHelper.addUser(
                            username.getText().toString(),
                            pass.getText().toString(),
                            fullName.getText().toString(),
                            email.getText().toString(),
                            cell.getText().toString(),
                            userType,
                            address.getText().toString(),
                            city.getText().toString());
                    if (isInserted) {
                        Toast.makeText(IndividualUser.this, "User added", Toast.LENGTH_SHORT).show();
                        if(userType.equals("Provider")){
                            startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                        }
                        else{
                            startActivity(new Intent(IndividualUser.this, CustomerMenu.class));
                        }

                    } else {
                        Toast.makeText(IndividualUser.this, "Error to add the User", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(IndividualUser.this, "Make sure all fields were filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();

            if (action.equals("VIEW")) {
                inflater.inflate(R.menu.menu, menu);
                inflater.inflate(R.menu.edit_delete, menu);
                return true;
            }
             else if (action.equals("EDIT")) {
                inflater.inflate(R.menu.menu, menu);
                return true;
            }
            else{
                return false;
            }


        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            switch (item.getItemId()) {
                case R.id.edit:
                    fullName.setEnabled(true);
                    pass.setEnabled(true);
                    cell.setEnabled(true);
                    address.setEnabled(true);
                    email.setEnabled(true);
                    city.setEnabled(true);
                    return true;
                case R.id.menu:
                    if(userType.equals("Provider")){

                        startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                    }
                    else if(userType.equals("Customer")){
                        startActivity(new Intent(IndividualUser.this, CustomerMenu.class));
                    }
                    return true;
                default:
                    return false;
            }
        }
    public boolean areEditTextsFilled () {
        boolean areFilled = true;
        ViewGroup viewGroup = findViewById(R.id.indiv_user);

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof EditText) {
                String text = ((EditText) view).getText().toString().trim();
                if (text.isEmpty()) {
                    areFilled = false;
                    break;
                }
            }
        }
        return areFilled;
    }


    }

