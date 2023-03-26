package com.example.autofix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class BookAppointment extends AppCompatActivity {

    String meth;
    String str="";
    StringBuilder date = new StringBuilder();
    String selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Spinner time = findViewById(R.id.spinnerTime);
        Button btnbook = findViewById(R.id.btnBook);
        Switch drop = findViewById(R.id.switchDrop);
        Switch pickup = findViewById(R.id.switchPick);
        CalendarView calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                date.append(Integer.toString(year) + "-");
                date.append(Integer.toString(month+1) + "-");
                date.append(Integer.toString(dayOfMonth));
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drop.isChecked()){
                    meth = "Drop-off";
                } else if (pickup.isChecked()) {
                    meth = "Pick-up";
                }
                else{
                    str+="You should select Drop-off or Pick-up method";
                }

                if(date.length() == 0){
                    str+="\n" + "You should select a date for the appointment";
                }


                if(time.getSelectedItem() == time.getItemAtPosition(0)){
                    str+= "you should select a valid time";
                }
                else{
                    selectedTime = time.getSelectedItem().toString();
                }
//                Toast.makeText(BookAppointment.this,tes,Toast.LENGTH_SHORT).show();



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
            startActivity(new Intent(BookAppointment.this, CustomerMenu.class));
            return true;
        }
        else{
            Toast.makeText(BookAppointment.this,"Action not identified",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void checkSwitch(View view){
        Switch dropoff = findViewById(R.id.switchDrop);
        Switch pickup = findViewById(R.id.switchPick);

        switch (view.getId()){
            case R.id.switchDrop:
                pickup.setChecked(false);
                dropoff.setChecked(true);
                break;
            case R.id.switchPick:
                dropoff.setChecked(false);
                break;
        }

    }
}