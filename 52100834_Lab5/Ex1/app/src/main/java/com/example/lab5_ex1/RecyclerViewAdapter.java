package com.example.lab5_ex1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<DataViewHolder>{
    List<Phone> phoneList;
    public RecyclerViewAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        return new DataViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.textView.setText(phoneList.get(position).name);
        holder.checkBox.setChecked(phoneList.get(position).isChecked);
        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) ->{
            phoneList.get(position).isChecked = isChecked;
                });

    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

}
