package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    String uName,uPass,uFullName,uEmail,uCell,uAddress,uCity;

    int userID;


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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        userID = sharedPreferences.getInt("USER_ID",0);
        Button btnSave = findViewById(R.id.btnUserSave);
        databaseHelper = new DatabaseHelper(this);
        action = sharedPreferences.getString("ACTION","");




        if (action.equals("REG") || action.equals("ADD")) {
            username.setEnabled(true);
            setFieldsEnabled();
        }
        else if(action.equals("EDIT") || action.equals("VIEW")){
            Cursor cursor = databaseHelper.selectAUser(userID);
            StringBuilder str = new StringBuilder();
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    username.setText(cursor.getString(1));
                    pass.setText(cursor.getString(2));
                    fullName.setText(cursor.getString(3));
                    cell.setText(cursor.getString(5));
                    email.setText(cursor.getString(4));
                    address.setText(cursor.getString(6));
                    city.setText(cursor.getString(7));
                }

            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            boolean isInserted, isUpdated;
            @Override
            public void onClick(View view) {

                if (areEditTextsFilled()) {
                    if (action.equals("EDIT")) {
                        getFieldsValue();
                        userType = "Customer";
                        isUpdated = databaseHelper.updateUser(userID,uName,uPass,uFullName,uEmail,uCell,userType,uAddress,uCity);
                        if(isUpdated){
                            Toast.makeText(IndividualUser.this, "User updated", Toast.LENGTH_SHORT).show();
                            setFieldsEmpty();
                        }
                        else{
                            Toast.makeText(IndividualUser.this, "Error to update the User", Toast.LENGTH_SHORT).show();
                        }
                    } else if(action.equals("ADD")) {
                        userType = "Customer";
                        getFieldsValue();
                        isInserted = databaseHelper.addUser(uName,uPass,uFullName,uEmail,uCell,userType,uAddress,uCity);
                        userType = "Provider";
                        if (isInserted) {
                            Toast.makeText(IndividualUser.this, "User added", Toast.LENGTH_SHORT).show();
                            if (userType.equals("Provider")) {
                                startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                            } else if (action.equals("ADD")) {
                                startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                            } else {
                                startActivity(new Intent(IndividualUser.this, CustomerMenu.class));
                            }

                        } else {
                            Toast.makeText(IndividualUser.this, "Error to add the User", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                        Toast.makeText(IndividualUser.this, "Make sure all fields were filled", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();

            if (action.equals("EDIT")|| action.equals("VIEW")) {
                inflater.inflate(R.menu.menu, menu);
                inflater.inflate(R.menu.edit_delete, menu);
                return true;
            }
             else if (action.equals("ADD")) {
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
                    setFieldsEnabled();
                    return true;
                case R.id.menu:
                    if(userType.equals("Provider")){

                        startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                    } else if (action.equals("ADD")) {
                        startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                    } else if(userType.equals("Customer")){
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

    public void setFieldsEnabled(){
        fullName.setEnabled(true);
        pass.setEnabled(true);
        cell.setEnabled(true);
        address.setEnabled(true);
        email.setEnabled(true);
        city.setEnabled(true);

    }

    public void setFieldsEmpty(){
        username.setText("");
        pass.setText("");
        fullName.setText("");
        cell.setText("");
        address.setText("");
        email.setText("");
        city.setText("");

    }


    public void getFieldsValue(){
        uName = username.getText().toString();
        uPass =  pass.getText().toString();
        uFullName = fullName.getText().toString();
        uEmail = email.getText().toString();
        uCell = cell.getText().toString();
        uAddress = address.getText().toString();
        uCity = city.getText().toString();
}

}

