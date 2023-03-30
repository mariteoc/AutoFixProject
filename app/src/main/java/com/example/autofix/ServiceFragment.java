package com.example.autofix;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ServiceFragment extends Fragment {

    TextView service, description,price;
    int serviceID;
    DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());

        getParentFragmentManager().setFragmentResultListener("SERVICE_ID", this, (requestKey, result) -> {
            serviceID = result.getInt("SERVICE_ID");


            Cursor cursor = databaseHelper.selectAService(serviceID);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    service.setText(cursor.getString(0));
                    description.setText(cursor.getString(1));
                    price.setText("$ "+ cursor.getString(2));

                }
            }
            else{
                service.setText("No service data");
                description.setText("");
                price.setText("");
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                return inflater.inflate(R.layout.fragment_service, container, false);
    }

    public void onViewCreated(View view, Bundle b) {
        super.onViewCreated(view, b);

        service = view.findViewById(R.id.txtServiceTitle);
        description = view.findViewById(R.id.txtServiceDesc);
        price = view.findViewById(R.id.txtServPrice);
    }

}