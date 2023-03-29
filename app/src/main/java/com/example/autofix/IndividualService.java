package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IndividualService extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    int providerID;
    EditText servTitle;
    EditText description;
    EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_service);
        servTitle = findViewById(R.id.editService);
        description = findViewById(R.id.editDescription);
        price = findViewById(R.id.editPrice);
        Button btnSave = findViewById(R.id.btnSaveServ);
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        providerID = sharedPreferences.getInt("PROV_ID",0);


        btnSave.setOnClickListener(new View.OnClickListener() {
            boolean isAdded;
            @Override
            public void onClick(View view) {
                if(areEditTextsFilled()) {
                    isAdded = databaseHelper.addService(
                            servTitle.getText().toString(),
                            providerID,
                            price.getText().toString(),
                            description.getText().toString());
                    if (isAdded) {
                        Toast.makeText(IndividualService.this, "Service added", Toast.LENGTH_SHORT).show();
                        servTitle.setText("");
                        description.setText("");
                        price.setText("");
                    } else {
                        Toast.makeText(IndividualService.this, "Error to add the service", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(IndividualService.this, "Make sure all fields were filled", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.edit_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {
            case R.id.edit:
                servTitle.setEnabled(true);
                description.setEnabled(true);
                price.setEnabled(true);
                return true;
            case R.id.menu:
                    startActivity(new Intent(IndividualService.this, ProviderMenu.class));
                return true;
            default:
                return false;
        }
    }

    public boolean areEditTextsFilled () {
        boolean areFilled = true;
        ViewGroup viewGroup = findViewById(R.id.indivServ);

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof EditText) {
                String text = ((EditText) view).getText().toString().trim();
                if (text.isEmpty()) {
                    areFilled = false;
                    break;
                }
            }
        }
        return areFilled;
    }


}