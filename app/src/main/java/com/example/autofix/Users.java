package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Users extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor= sharedPreferences.edit();
        Button btnView = findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("ACTION", "VIEW");
                editor.commit();
                startActivity(new Intent(Users.this, IndividualUser.class));
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.add:
//                Intent intent =new Intent(Users.this, IndividualUser.class);
//                intent.putExtra("REG", 2);
                editor.putString("ACTION", "EDIT");
                editor.commit();
                startActivity(new Intent(Users.this, IndividualUser.class));
                return true;
            case R.id.menu:
                startActivity(new Intent(Users.this, ProviderMenu.class));
                return true;
            default:
                return false;
        }

        }


}