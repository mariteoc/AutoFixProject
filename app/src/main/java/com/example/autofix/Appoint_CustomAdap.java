package com.example.autofix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Appoint_CustomAdap extends RecyclerView.Adapter<Appoint_CustomAdap.MyViewHolder>{
    Context context;
    ArrayList<AppointmentsModel> appointmentsModels;
    final RecyclerViewInt recyclerViewInt;



    public Appoint_CustomAdap(Context context, ArrayList<AppointmentsModel> appointmentsModels, RecyclerViewInt recyclerViewInt){
        this.context = context;
        this.appointmentsModels = appointmentsModels;
        this.recyclerViewInt = recyclerViewInt;

    }


    @NonNull
    @Override
    public Appoint_CustomAdap.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate(R.layout.appointment_item,parent,false);
        return new Appoint_CustomAdap.MyViewHolder(view,recyclerViewInt);
    }

    @Override
    public void onBindViewHolder(@NonNull Appoint_CustomAdap.MyViewHolder holder, int position) {
        holder.txtDate.setText(appointmentsModels.get(position).getDate());
        holder.txtService.setText(appointmentsModels.get(position).getService() + " Provider: " +
                appointmentsModels.get(position).getProvider());
    }

    @Override
    public int getItemCount() {

        return appointmentsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtDate;
        TextView txtService;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInt recyclerViewInt){
            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDateHistory);
            txtService = itemView.findViewById(R.id.txtServiceHistory);

        }
    }



}
