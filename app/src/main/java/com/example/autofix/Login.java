package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        Intent intent = getIntent();
        if(intent!=null){
            user = intent.getStringExtra("USERTYPE");
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regIntent = new Intent();
                if(user.equals("Customer")){
                    regIntent = new Intent(Login.this, CustomerProfile.class);
                }
                else if(user.equals("Provider")){
                    regIntent = new Intent(Login.this, RegisterServProvider.class);
                }
                regIntent.putExtra("REG", 1);
                startActivity(regIntent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.equals("Customer")){
                    startActivity(new Intent(Login.this, CustomerMenu.class));
                }
                else if(user.equals("Provider")){
                    startActivity(new Intent(Login.this, ProviderMenu.class));
                }

            }
        });
    }
}