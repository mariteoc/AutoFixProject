package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class ServiceProviders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers);
        Button btnSelect = findViewById(R.id.btnSelect);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceProviders.this, BookAppointment.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu){
            startActivity(new Intent(ServiceProviders.this, CustomerMenu.class));
            return true;
        }
        else{
            Toast.makeText(ServiceProviders.this,"Action not identified",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void GetList(View v){
        ListView lview = findViewById(R.id.listView);

        List<Map<String,String>> dataList = null;



        String[] fromW = {"ServiceProvider", "Address"};
        int[] toW = {R.id.txtProviderItem,R.id.txtProviderAddress};


    }
}