package com.example.bai3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView simpleList;
    Button btnDeleteAll;
    Button btnDeleteSelected;
    MyAdapter adapter;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int randomNumber = new Random().nextInt(9) + 1;
//        Toast.makeText(this, Integer.toString(randomNumber) + " lines", Toast.LENGTH_SHORT).show();

        List<Item> listString = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            listString.add(new Item("Item" + i));
        }

        adapter = new MyAdapter(listString);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDeleteSelected = findViewById(R.id.btnDeleteSelected);

        btnDeleteAll.setOnClickListener(this);
        btnDeleteSelected.setOnClickListener(this);

        simpleList = findViewById(R.id.lvString);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ((TextView)view.findViewById(R.id.textView)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

//        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.listString.removeAll(adapter.listString);
//                adapter.notifyDataSetChanged();
//            }
//        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case(R.id.btnDeleteAll): {
                adapter.listString.removeAll(adapter.listString);
                adapter.notifyDataSetChanged();
            }
            case(R.id.btnDeleteSelected): {
                for (int i = 0; i < adapter.listString.size(); i++) {
                    if (adapter.listString.get(i).checked) {
                        adapter.listString.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}