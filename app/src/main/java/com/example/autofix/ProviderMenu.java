package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ProviderMenu extends AppCompatActivity {

    String[] items = {"Users","Appointments","Reminders",
            "Service Details","Service Reports","Logout"};
    int[] images = {R.drawable.profile,R.drawable.book,R.drawable.remind,
            R.drawable.view,R.drawable.report,R.drawable.logout};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_menu);

        ArrayList<HashMap<String, String>> options = new ArrayList<>();

        for(int i=0;i< items.length;i++){
            HashMap<String,String> menu = new HashMap<>();
            menu.put("Option", items[i]);
            menu.put("icon", Integer.toString(images[i]));
            options.add(menu);
        }

        String[] from = {"icon","Option"};
        int[] to = {R.id.imageView,R.id.textView};

        SimpleAdapter adapter = new SimpleAdapter(ProviderMenu.this, options,R.layout.item_layout, from, to);

        ListView lView = findViewById(R.id.listViewProvider);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        startActivity(new Intent(ProviderMenu.this,All_Users.class));
                        break;
                    case 1:
                        startActivity(new Intent(ProviderMenu.this,Appointments.class));
                        break;
                    case 2:
                        startActivity(new Intent(ProviderMenu.this,Reminders.class));
                        break;
                    case 3:
                        startActivity(new Intent(ProviderMenu.this,IndividualService.class));
                        break;
                    case 4:
                        startActivity(new Intent(ProviderMenu.this,ServiceReport.class));
                        break;
                    case 5:
                        startActivity(new Intent(ProviderMenu.this,MainActivity.class));
                        break;
                }
            }
        });

    }
}