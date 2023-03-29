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

public class User_CustomAdapter extends RecyclerView.Adapter<User_CustomAdapter.MyViewHolder> implements Filterable {
    final RecyclerViewInt recyclerViewInt;
    Context context;
    ArrayList<UsersModel> usersModels;
    ArrayList<UsersModel> usersModelsFull;


    public User_CustomAdapter(Context context, ArrayList<UsersModel> usersModels,
                              RecyclerViewInt recyclerViewInterface){
        this.context = context;
        this.usersModels = usersModels;
        usersModelsFull = new ArrayList<>(usersModels);
        this.recyclerViewInt = recyclerViewInterface;

    }


    @NonNull
    @Override
    public User_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate(R.layout.user_item,parent,false);
        return new User_CustomAdapter.MyViewHolder(view,recyclerViewInt);
    }

    @Override
    public void onBindViewHolder(@NonNull User_CustomAdapter.MyViewHolder holder, int position) {
        holder.txtUsername.setText(usersModels.get(position).getUsername());
        holder.txtUserEmail.setText(usersModelsFull.get(position).getEmail());
    }

    @Override
    public int getItemCount() {

        return usersModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtUsername;
        TextView txtUserEmail;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInt recyclerViewInt){
            super(itemView);

            txtUsername = itemView.findViewById(R.id.txtUser);
            txtUserEmail = itemView.findViewById(R.id.txtUserEmail);
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
        }
    }

    @Override
    public Filter getFilter() {
        return usersFilter;
    }

    private Filter usersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<UsersModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(usersModelsFull);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(UsersModel user : usersModelsFull){
                    if(user.getUsername().toLowerCase().contains(filterPattern)){
                        filteredList.add(user);
                    }
                }
            }
            FilterResults result = new FilterResults();
            result.values = filteredList;

            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            usersModels.clear();
            usersModels.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };
}
