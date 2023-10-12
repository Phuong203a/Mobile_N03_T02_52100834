package com.example.bai4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRV;
    List<Item> itemList = new ArrayList<>();
    Button btnAdd;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd = findViewById(R.id.btnAdd);

        MyAdapter adapter = new MyAdapter(itemList);
        rvRV = findViewById(R.id.rvRV);
        rvRV.setAdapter(adapter);
        rvRV.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = adapter.itemList.size();
                for (int i = 1; i <= 5; i++) {
                    itemList.add(new Item("Person" + (size+1), "user" + (size+1) + "@gmail.com"));
                }
                adapter.notifyItemRangeInserted(size, adapter.itemList.size());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = adapter.itemList.size();
                if (size < 5) {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 1; i <= 5; i++) {
                    adapter.itemList.remove(size - i);
                }
                    adapter.notifyDataSetChanged();
            }
        });
    }
}