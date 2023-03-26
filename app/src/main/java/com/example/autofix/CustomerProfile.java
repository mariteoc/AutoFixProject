package com.example.autofix;

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

    int reg;
    DatabaseHelperOld databaseHelperOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnEdit = findViewById(R.id.btnEdit);
        EditText fullName = findViewById(R.id.editFullName);
        EditText cellphone = findViewById(R.id.editCellphone);
        EditText address = findViewById(R.id.editAddress);
        EditText email = findViewById(R.id.editEmail1);
        EditText username = findViewById(R.id.editUsername);
        Intent intent = getIntent();
        if(intent != null){
            reg = intent.getIntExtra("REG",0);
        }

        if(reg == 1){
            btnEdit.setVisibility(View.GONE);
            username.setEnabled(true);
            fullName.setEnabled(true);
            cellphone.setEnabled(true);
            address.setEnabled(true);
            email.setEnabled(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            boolean isUpdated;
            String user;
            @Override
            public void onClick(View view) {
//                if(reg ==0){
//                    isInserted= databaseHelper.addData(username.getText().toString(),
//                            fullName.getText().toString(),cellphone.getText().toString(),
//                            address.getText().toString(),email.getText().toString());
//                    if(isInserted){
//                        Toast.makeText(CustomerProfile.this, "User created",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(CustomerProfile.this, "Error to create user",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    user = username.getText().toString();
//                    isUpdated = databaseHelper.updateData(username);
//                    if(isUpdated){
//                        Toast.makeText(CustomerProfile.this, "User information was updated",
//                                Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Toast.makeText(CustomerProfile.this, "Error to update user information",
//                                Toast.LENGTH_LONG).show();
//                    }


//                }
                startActivity(new Intent(CustomerProfile.this,CustomerMenu.class));
            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
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
        if(reg == 0){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
            return true;
        }
        else{
            return false;
        }

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