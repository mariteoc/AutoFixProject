package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class IndividualUser extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_user);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        inflater.inflate(R.menu.edit_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText fullName = findViewById(R.id.editFullN);
        EditText cell = findViewById(R.id.editCellNumb);
        EditText address = findViewById(R.id.editUserAdd);
        EditText email = findViewById(R.id.editEmail);
        switch (item.getItemId()) {
            case R.id.edit:
                fullName.setEnabled(true);
                cell.setEnabled(true);
                address.setEnabled(true);
                email.setEnabled(true);
                return true;
            case R.id.menu:
                startActivity(new Intent(IndividualUser.this, ProviderMenu.class));
                return true;
            default:
                return false;
        }
    }
}