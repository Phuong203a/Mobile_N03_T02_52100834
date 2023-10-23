package com.example.ex3;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewEmail;

    RecyclerViewAdapter adapter;

    DataViewHolder(View view) {
        super(view);
        textViewName = view.findViewById(R.id.textViewName);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        this.setIsRecyclable(false);

    }

    public DataViewHolder linkAdapter(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

}

