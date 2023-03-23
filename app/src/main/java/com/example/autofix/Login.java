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
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));
        Button login = findViewById(R.id.btnLogin);
        Intent intent = getIntent();
        if(intent!=null){
            user = intent.getStringExtra("USERTYPE");
        }


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