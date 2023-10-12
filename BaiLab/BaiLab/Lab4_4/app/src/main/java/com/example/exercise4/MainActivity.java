package com.example.exercise4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> items;
    ArrayList<Boolean> selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //tao du lieu
        items = new ArrayList<>();
        selected = new ArrayList<>();

        int random = new Random().nextInt(91) + 10;
        for(int i = 1; i <= random; i++){
            items.add("PC "+i);
            selected.add(false);
        }

        //set adapter
        ItemAdapter itemAdapter = new ItemAdapter(items, selected);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(itemAdapter);

    }
}