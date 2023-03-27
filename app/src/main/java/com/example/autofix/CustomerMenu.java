package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerMenu extends AppCompatActivity {

    String[] items = {"Profile","Service Providers","Book an Appointment",
            "View an Appointment","View Service History","Logout"};
    int[] images = {R.drawable.profile,R.drawable.provider,R.drawable.book,
    R.drawable.view,R.drawable.history,R.drawable.logout};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<HashMap<String, String>> options = new ArrayList<>();

        for(int i=0;i< items.length;i++){
            HashMap<String,String> menu = new HashMap<>();
            menu.put("Option", items[i]);
            menu.put("icon", Integer.toString(images[i]));
            options.add(menu);
        }

        String[] from = {"icon","Option"};
        int[] to = {R.id.imageView,R.id.textView};

        SimpleAdapter adapter = new SimpleAdapter(CustomerMenu.this, options,R.layout.item_layout, from, to);

        ListView lView = findViewById(R.id.listView);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        editor.putString("ACTION","VIEW");
                        editor.commit();
                        startActivity(new Intent(CustomerMenu.this,IndividualUser.class));
                        break;
                    case 1:
                        //startActivity(new Intent(CustomerMenu.this,ServiceProviders.class));
                        startActivity(new Intent(CustomerMenu.this,ServProviders.class));
                        break;
                    case 2:
                        startActivity(new Intent(CustomerMenu.this,BookAppointment.class));
                        break;
                    case 3:
                        startActivity(new Intent(CustomerMenu.this,Appointments.class));
                        break;
                    case 4:
                        startActivity(new Intent(CustomerMenu.this,ServiceHistory.class));
                        break;
                    case 5:
                        startActivity(new Intent(CustomerMenu.this,MainActivity.class));
                        break;
                }
            }
        });
    }
}