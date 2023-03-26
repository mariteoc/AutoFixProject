package com.example.autofix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterServProvider extends AppCompatActivity {

    DatabaseHelperOld databaseHelperOld;
    StringBuilder message= new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_serv_provider);
        EditText serviceProvider = findViewById(R.id.editServProvName);
        EditText phone = findViewById(R.id.editPhoneNumb);
        EditText city = findViewById(R.id.editProvCity);
        Button btnSave = findViewById(R.id.btnProvSave);
        Button btnCreate = findViewById(R.id.btnCreateUser);
        databaseHelperOld = new DatabaseHelperOld(this);
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
                Intent intent = new Intent(RegisterServProvider.this, IndividualUser.class);
                intent.putExtra("REG", 1);
                startActivity(intent);
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
                    isInserted = databaseHelperOld.addServProvData(serviceProvider.getText().toString(),
                            phone.getText().toString(),city.getText().toString());
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