package com.example.ex2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterView extends RecyclerView.ViewHolder {
    TextView textView;
    private Adapter adapter;

    AdapterView(View view) {
        super(view);
        textView = view.findViewById(R.id.textView);
        view.findViewById(R.id.button).setOnClickListener(v -> {
            adapter.dataList.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public AdapterView linkAdapter(Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

}

