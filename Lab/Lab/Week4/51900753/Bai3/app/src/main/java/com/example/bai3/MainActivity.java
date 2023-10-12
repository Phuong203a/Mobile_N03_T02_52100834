package com.example.bai3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRV;
    List<Item> itemList = new ArrayList<>();
    Button btnRmAll;
    Button btnRmSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRmAll = findViewById(R.id.btnRmAll);
        btnRmSelected = findViewById(R.id.btnRmSelected);

        int n = new Random().nextInt(30) + 20;

        for (int i = 1; i <= n; i++) {
            itemList.add(new Item("Person" + i, false));
        }

        MyAdapter adapter = new MyAdapter(itemList);
        rvRV = findViewById(R.id.rvRV);
        rvRV.setAdapter(adapter);
        rvRV.setLayoutManager(new LinearLayoutManager(this));

        btnRmAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = adapter.itemList.size();
                adapter.itemList.clear();
                adapter.notifyItemRangeRemoved(0, count);
            }
        });

        btnRmSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = adapter.itemList.size() - 1; i >= 0 ; i--) {
                    if (adapter.itemList.get(i).checked) {
                        adapter.itemList.remove(i);

                        adapter.notifyItemRemoved(i);
//                        adapter.notifyItemRangeChanged(i, adapter.itemList.size());
                    }
                }
            }
        });
    }
}