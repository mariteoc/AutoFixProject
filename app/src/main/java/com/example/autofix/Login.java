package com.example.autofix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    String userType;
    DatabaseHelper databaseHelper;
    String message= "User or password invalid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = findViewById(R.id.editUsername);
        EditText password = findViewById(R.id.editUserPass);
        Button login = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        userType = sharedPreferences.getString("USERTYPE","");
        databaseHelper = new DatabaseHelper(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error!");
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regIntent = new Intent();
                if(userType.equals("Customer")){
                    regIntent = new Intent(Login.this, IndividualUser.class);
                }
                else if(userType.equals("Provider")){
                    regIntent = new Intent(Login.this, RegisterServProvider.class);
                }
                editor.putString("ACTION","REG");
                editor.commit();
                startActivity(regIntent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    username.setError("Username is required");
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password is required");
                }
                else{
                    String pass="";
                    String user = "";
                    Cursor cursor = databaseHelper.verifyUser(username.getText().toString());
                    if(cursor.getCount()>0) {
                        while (cursor.moveToNext()) {
                            user = cursor.getString(0);
                            pass = cursor.getString(1);
                            userType = cursor.getString(2);
                        }

                        if (user.equals(username.getText().toString())) {
                            if (pass.equals(password.getText().toString())) {
                                if (userType.equals("Customer")) {
                                    startActivity(new Intent(Login.this, CustomerMenu.class));
                                } else if (userType.equals("Provider")) {
                                    startActivity(new Intent(Login.this, ProviderMenu.class));
                                }
                            }
                            else {
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    }
                    else{
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }


                }






            }
        });
    }
}