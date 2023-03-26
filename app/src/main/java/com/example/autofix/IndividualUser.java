package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class IndividualUser extends AppCompatActivity {

    int reg;
    EditText fullName;
    EditText cell;
    EditText address;
    EditText email;
    EditText username;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_user);
        fullName = findViewById(R.id.editFullN);
        cell = findViewById(R.id.editCellNumb);
        address = findViewById(R.id.editUserAdd);
        email = findViewById(R.id.editEmail);
        username = findViewById(R.id.editUserN);
        pass = findViewById(R.id.editPassword);
        Button btnSave = findViewById(R.id.btnUserSave);
        Intent intent = getIntent();
        if(intent != null){
            reg = intent.getIntExtra("REG",0);
        }

        if(reg == 1 || reg == 2) {
            username.setEnabled(true);
            pass.setEnabled(true);
            fullName.setEnabled(true);
            cell.setEnabled(true);
            address.setEnabled(true);
            email.setEnabled(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(areEditTextsFilled()){

                }
                else{

                }


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        if(reg ==0){
            inflater.inflate(R.menu.menu,menu);
            inflater.inflate(R.menu.edit_delete,menu);
            return true;
        } else if (reg == 2) {
            inflater.inflate(R.menu.menu,menu);
            return true;
        } else{
            return false;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit:
                fullName.setEnabled(true);
                pass.setEnabled(true);
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

    public boolean areEditTextsFilled(){
        boolean areFilled = true;
        ViewGroup viewGroup = findViewById(R.id.indiv_user);

        for(int i=0;i < viewGroup.getChildCount();i++){
            View view = viewGroup.getChildAt(i);

            if(view instanceof EditText){
                String text = ((EditText) view).getText().toString().trim();
                if(text.isEmpty()){
                    areFilled = false;
                    break;
                }
            }
        }
        return areFilled;
    }
}