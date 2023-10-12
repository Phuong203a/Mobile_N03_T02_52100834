package com.example.bai1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRV;
    List<String> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int n = new Random().nextInt(30) + 10;


        for (int i = 1; i <= n; i++) {
            itemList.add("Item" + i);
        }

        MyAdapter adapter = new MyAdapter(itemList, this);
        rvRV = findViewById(R.id.rvRV);
        rvRV.setAdapter(adapter);
        rvRV.setLayoutManager(new LinearLayoutManager(this));
    }
}