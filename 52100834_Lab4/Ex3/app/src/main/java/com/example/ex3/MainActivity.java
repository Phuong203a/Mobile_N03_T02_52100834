package com.example.ex3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Button removeAllButton;
    private Button removeSelectedButton;
    private List<Phone> phoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        removeAllButton = findViewById(R.id.buttonRemoveAll);
        removeSelectedButton = findViewById(R.id.btnRemoveSelected);
        phoneList = generatePhone();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phoneList);
        recyclerView.setAdapter(adapter);

        removeAllButton.setOnClickListener(view -> {
            phoneList.clear();
            adapter.notifyDataSetChanged();
        });
        removeSelectedButton.setOnClickListener(view -> {
            List<Phone> itemsToRemove = new ArrayList<>();
            for (Phone phone : phoneList) {
                if (phone.isChecked) {
                    itemsToRemove.add(phone);
                }
            }
            phoneList.removeAll(itemsToRemove);
            adapter.notifyDataSetChanged();
        });
    }

    private List<Phone> generatePhone() {
        List<Phone> phoneList = new ArrayList<>();
        Random r = new Random();
        int num = r.nextInt(15);
        Phone phone;
        for (int i = 0; i < num; i++) {
            phone = new Phone();
            phone.id = i;
            phone.name = "Mobile " + i;
            phoneList.add(phone);
        }
        return phoneList;
    }
}


