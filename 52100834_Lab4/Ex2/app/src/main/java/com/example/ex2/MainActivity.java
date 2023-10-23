package com.example.ex2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private Button remove;
    private RecyclerView recyclerView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataList = generateRandomData(20); // Số lượng phần tử ngẫu nhiên
        recyclerView= findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(dataList);
        recyclerView.setAdapter(adapter);
    }

        private List<String> generateRandomData ( int count){
        List<String> data = new ArrayList<>();
        Random random = new Random();
        count = random.nextInt(count + 1);

        for (int i = 1; i <= count; i++) {
            data.add("Item " + i);
        }
        return data;

    }
}