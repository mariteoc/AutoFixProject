package com.example.autofix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterServProvider extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    StringBuilder message= new StringBuilder();
    int provID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_serv_provider);
        EditText serviceProvider = findViewById(R.id.editServProvName);
        EditText phone = findViewById(R.id.editPhoneNumb);
        EditText city = findViewById(R.id.editProvCity);
        Button btnSave = findViewById(R.id.btnProvSave);
        Button btnCreate = findViewById(R.id.btnCreateUser);
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error!");
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("ACTION", "REG");
                editor.commit();
                startActivity(new Intent(RegisterServProvider.this, IndividualUser.class));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(serviceProvider.getText().toString() )){
                    message.append("Missing Service Provider Name");
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    message.append("\n" + "Missing phone number");
                }
                if(TextUtils.isEmpty(city.getText().toString())){
                    message.append("\n" + "Missing the city");
                }
                if(message.length() != 0){
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    message.setLength(0);
                }
                else{
                    isInserted = databaseHelper.addServiceProvider(serviceProvider.getText().toString(),
                            phone.getText().toString(),city.getText().toString());
                    Cursor cursor = databaseHelper.selectAProvider(serviceProvider.getText().toString());
                    cursor.moveToFirst();
                    provID = cursor.getInt(0);
                    editor.putInt("PROVIDER_ID",provID);
                    editor.apply();
                    if(isInserted){
                        Toast.makeText(RegisterServProvider.this,"Service Provider Saved", Toast.LENGTH_SHORT).show();
                        serviceProvider.setText("");
                        phone.setText("");
                        city.setText("");
                        btnCreate.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(RegisterServProvider.this,"Error to save the Service Provider", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}