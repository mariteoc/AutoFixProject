package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
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

import com.google.android.material.tabs.TabLayout;

public class Appointments extends AppCompatActivity implements FragCommunicator {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
//        inflater.inflate(R.menu.edit_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Context context = getApplicationContext();
        SharedPreferences editor = PreferenceManager.getDefaultSharedPreferences(context);
        String str = editor.getString("USERTYPE","");

        if (item.getItemId() == R.id.menu){
            if (str.equals("Customer")){
                startActivity(new Intent(Appointments.this, CustomerMenu.class));

            }
            if (str.equals("Provider")){
                startActivity(new Intent(Appointments.this, ProviderMenu.class));
            }
            return true;
        }
        else{
            Toast.makeText(Appointments.this,"Action not identified",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void sendData(int serviceID) {

    }

//    @Override
//    public void sendData(int serviceID) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putInt("SERVICE_ID",serviceID);
//        ServiceFragment serviceFragment = (ServiceFragment) viewPagerAdapter.createFragment(1);
//        serviceFragment.setArguments(bundle);
//        viewPagerAdapter.notifyDataSetChanged();
//        ft.commitNowAllowingStateLoss();
//
//
//
//    }

}