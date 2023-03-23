package com.example.autofix;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        Button edit = findViewById(R.id.btnEdit);
        EditText fullName = findViewById(R.id.editFullName);
        EditText cellphone = findViewById(R.id.editCellphone);
        EditText address = findViewById(R.id.editAddress);
        EditText email = findViewById(R.id.editEmail1);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName.setEnabled(true);
                cellphone.setEnabled(true);
                address.setEnabled(true);
                email.setEnabled(true);

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
                startActivity(new Intent(CustomerProfile.this, CustomerMenu.class));
                return true;
        }
        else{
            Toast.makeText(CustomerProfile.this,"Action not identified",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}