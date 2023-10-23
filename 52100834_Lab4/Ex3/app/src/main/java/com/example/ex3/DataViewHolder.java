package com.example.ex3;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    CheckBox checkBox;
    RecyclerViewAdapter adapter;

    DataViewHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.textTextView);
        checkBox = view.findViewById(R.id.checkBox);
        this.setIsRecyclable(false);


    }

    public DataViewHolder linkAdapter(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

}

