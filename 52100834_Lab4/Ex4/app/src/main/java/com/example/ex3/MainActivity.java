package com.example.ex3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    int numButton = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycleView);

        Random random = new Random();
        numButton = random.nextInt(91);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        CustomAdapter customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);
    }
    private class CustomAdapter extends  RecyclerView.Adapter<DataViewHolder> {

        @NonNull
        @Override
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
            return new DataViewHolder(view).linkAdapter(this);
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            holder.toggleButton.setChecked(!holder.toggleButton.isChecked());
        }

        @Override
        public int getItemCount() {
            return numButton;
        }
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        ToggleButton toggleButton;
        CustomAdapter adapter;
        DataViewHolder(View view) {
            super(view);
            toggleButton = view.findViewById(R.id.toggleButton);
            this.setIsRecyclable(false);
        }

        public DataViewHolder linkAdapter(CustomAdapter adapter) {
            this.adapter = adapter;
            return this;
        }

    }

}



