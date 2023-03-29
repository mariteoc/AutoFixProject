package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        setTitle("Services");
        Button next = findViewById(R.id.btnNext);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        RadioButton rdb1 = (RadioButton) findViewById(R.id.rdbServ1);
        RadioButton rdb2 = (RadioButton) findViewById(R.id.rdbServ2);
        RadioButton rdb3 = (RadioButton) findViewById(R.id.rdbServ3);
        RadioButton rdb4 = (RadioButton) findViewById(R.id.rdbServ4);
        RadioButton rdb5 = (RadioButton) findViewById(R.id.rdbServ5);
        RadioButton rdb6 = (RadioButton) findViewById(R.id.rdbServ6);

        next.setOnClickListener(new View.OnClickListener() {
            String radio;
            @Override
            public void onClick(View v) {
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override

                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.rdbServ1)
                          radio = rdb1.getText().toString();
                        if(checkedId == R.id.rdbServ2)
                            radio = rdb2.getText().toString();
                        if(checkedId == R.id.rdbServ3)
                            radio = rdb3.getText().toString();
                        if(checkedId == R.id.rdbServ4)
                            radio = rdb4.getText().toString();
                        if(checkedId == R.id.rdbServ5)
                            radio = rdb5.getText().toString();
                        if(checkedId == R.id.rdbServ6)
                            radio = rdb6.getText().toString();
                    }
                });
                Intent intent = new Intent(Services.this,BookAppointment.class);
                intent.putExtra("radio",radio);
                startActivity(intent);
            }
        });
    }
}