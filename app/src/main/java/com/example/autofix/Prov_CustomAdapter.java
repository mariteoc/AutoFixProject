package com.example.autofix;

import android.content.Context;
import android.content.Intent;
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
import java.util.Locale;

public class Prov_CustomAdapter extends RecyclerView.Adapter<Prov_CustomAdapter.MyViewHolder> implements Filterable {

    final RecyclerViewInt recyclerViewInt;
    Context context;
    ArrayList<ProvidersModel> providersModels;
    ArrayList<ProvidersModel> providersModelsFull;


    public Prov_CustomAdapter(Context context, ArrayList<ProvidersModel> providersModels,
                              RecyclerViewInt recyclerViewInterface){
        this.context = context;
        this.providersModels = providersModels;
        providersModelsFull = new ArrayList<>(providersModels);
        this.recyclerViewInt = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Prov_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate(R.layout.servprov_item,parent,false);
        return new Prov_CustomAdapter.MyViewHolder(view,recyclerViewInt);
    }

    @Override
    public void onBindViewHolder(@NonNull Prov_CustomAdapter.MyViewHolder holder, int position) {
        holder.txtViewName.setText(providersModels.get(position).getProvName());
        holder.txtViewData.setText(providersModels.get(position).getProvCity() + " -- Phone: " + providersModels.get(position).getProvPhone());
    }

    @Override
    public int getItemCount() {

        return providersModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewName;
        TextView txtViewData;
        TextView txtViewID;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInt recyclerViewInt){
            super(itemView);

            txtViewName = itemView.findViewById(R.id.txtProvider);
            txtViewData = itemView.findViewById(R.id.txtProvData);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInt != null){
                        int pos = getBindingAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInt.onItemClick(pos);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    v.getContext().startActivity(new Intent(v.getContext(),Services.class));
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return providersFilter;
    }

    private Filter providersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ProvidersModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(providersModelsFull);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(ProvidersModel provider : providersModelsFull){
                    if(provider.getProvCity().toLowerCase().startsWith(filterPattern)){
                        filteredList.add(provider);
                    }
                }
            }
            FilterResults result = new FilterResults();
            result.values = filteredList;

            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            providersModels.clear();
            providersModels.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };
}
