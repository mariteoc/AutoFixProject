package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Users extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
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
                startActivity(new Intent(Users.this, IndividualUser.class));
                return true;
            case R.id.menu:
                startActivity(new Intent(Users.this, ProviderMenu.class));
                return true;
            default:
                return false;
        }

        }
//        if (item.getItemId() == R.id.menu){
//            startActivity(new Intent(Users.this, ProviderMenu.class));
//            return true;
//        }
//        else{
//            Toast.makeText(Users.this,"Action not identified",Toast.LENGTH_SHORT).show();
//            return false;
//        }

}