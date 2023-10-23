package com.example.ex3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<DataViewHolder>{
    List<User> userList;
    public RecyclerViewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        return new DataViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.textViewName.setText(userList.get(position).name);
        holder.textViewEmail.setText(userList.get(position).email);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
