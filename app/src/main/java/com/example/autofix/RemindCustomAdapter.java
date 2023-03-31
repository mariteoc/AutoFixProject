package com.example.autofix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RemindCustomAdapter extends RecyclerView.Adapter<RemindCustomAdapter.MyViewHolder>{
    Context context;
    ArrayList<RemindersModel> remindersModels;
    final RecyclerViewInt recyclerViewInt;



    public RemindCustomAdapter(Context context, ArrayList<RemindersModel> remindersModels, RecyclerViewInt recyclerViewInt){
        this.context = context;
        this.remindersModels = remindersModels;
        this.recyclerViewInt = recyclerViewInt;

    }


    @NonNull
    @Override
    public RemindCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate(R.layout.reminder_item,parent,false);
        return new RemindCustomAdapter.MyViewHolder(view,recyclerViewInt);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindCustomAdapter.MyViewHolder holder, int position) {
        holder.txtDate.setText(remindersModels.get(position).getDate() + "    " +remindersModels.get(position).getTime());
        holder.txtService.setText(remindersModels.get(position).getService());
    }

    @Override
    public int getItemCount() {

        return remindersModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtDate;
        TextView txtService;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInt recyclerViewInt){
            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDateApp);
            txtService = itemView.findViewById(R.id.txtServiceApp);

        }
    }

}
