package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Services extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    TextView txtOutput;
    StringBuilder test = new StringBuilder();
    int serviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        setTitle("Services");
        Button next = findViewById(R.id.btnNext);
        databaseHelper = new DatabaseHelper(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
//        RadioButton rdb1 = (RadioButton) findViewById(R.id.rdbServ1);
//        RadioButton rdb2 = (RadioButton) findViewById(R.id.rdbServ2);
//        RadioButton rdb3 = (RadioButton) findViewById(R.id.rdbServ3);
//        RadioButton rdb4 = (RadioButton) findViewById(R.id.rdbServ4);
//        RadioButton rdb5 = (RadioButton) findViewById(R.id.rdbServ5);
//        RadioButton rdb6 = (RadioButton) findViewById(R.id.rdbServ6);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int provID = sharedPreferences.getInt("PROVIDER_ID", 0);


        Cursor cursor = databaseHelper.selectProviderServices(provID);
        if (cursor.getCount() > 0) {
            int i = 0;
            String varName;
            ArrayList<RadioButton> radioButtons = new ArrayList<>();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            while (cursor.moveToNext()) {
                RadioButton rdb = new RadioButton(this);
                rdb.setText(cursor.getString(1));
                rdb.setId(cursor.getInt(0));
                rdb.setTextSize(20);
                rdb.setTextColor(getResources().getColor(R.color.serv_text));
                rdb.setPadding(1,10,1,10);
                rdb.setGravity(Gravity.CENTER);
//                radioGroup.setLayoutParams(layoutParams);
                radioGroup.addView(rdb);
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton radioButton = findViewById(i);
                serviceID = radioButton.getId();

            }

        });

next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Services.this,BookAppointment.class);
                intent.putExtra("SERV_ID",serviceID);
                startActivity(intent);

    }
});



//
//            }
//        });
//    }
    }
}