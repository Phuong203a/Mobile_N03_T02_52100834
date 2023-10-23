package com.example.ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends  RecyclerView.Adapter<AdapterView>{
    List<String> dataList;

    public Adapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item,parent,false);
        return new AdapterView(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView holder, int position) {
        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
