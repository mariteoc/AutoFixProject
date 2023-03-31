package com.example.autofix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ServiceReport extends AppCompatActivity {

    String message = "Appointment number invalid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_report);
        Button report = findViewById(R.id.btnReportGo);
        EditText appointment = findViewById(R.id.editAppNumber);
        TextView OutServProv = findViewById(R.id.txtOutServProv);
        TextView OutPhone = findViewById(R.id.txtOutPhone);
        TextView OutCust = findViewById(R.id.txtOutCustomer);
        TextView OutTime = findViewById(R.id.txtOutTime);
        TextView OutDate= findViewById(R.id.txtOutDate);
        TextView OutServExec = findViewById(R.id.txtOutServExec);
        TextView OutCity = findViewById(R.id.txtOutCity);
        TextView OutEmail = findViewById(R.id.txtOutEmail);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error!");
        builder.setMessage(message);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(appointment.getText().toString())){
                    appointment.setError("Appointment Number is required");
                }
                else {
                    String servProv = "";
                    String city = "";
                    String servExec = "";
                    String date = "";
                    String time = "";
                    String customer = "";
                    String phone = "";
                    String email = "";
                    Cursor cursor = databaseHelper.generateReport(Integer.parseInt(appointment.getText().toString()));
                    if(cursor.getCount() >0){
                        while(cursor.moveToNext()){
                            servProv = cursor.getString(1);
                            city = cursor.getString(2);
                            servExec = cursor.getString(0);
                            date = cursor.getString(3);
                            time = cursor.getString(4);
                            customer = cursor.getString(5);
                            phone = cursor.getString(6);
                            email = cursor.getString(7);

                            OutServProv.setText(servProv);
                            OutCity.setText(city);
                            OutServExec.setText(servExec);
                            OutDate.setText(date);
                            OutTime.setText(time);
                            OutCust.setText(customer);
                            OutPhone.setText(phone);
                            OutEmail.setText(email);
                        }
                    }
                    else{
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
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
            startActivity(new Intent(ServiceReport.this, ProviderMenu.class));
            return true;
        }
        else{
            Toast.makeText(ServiceReport.this,"Action not identified",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}