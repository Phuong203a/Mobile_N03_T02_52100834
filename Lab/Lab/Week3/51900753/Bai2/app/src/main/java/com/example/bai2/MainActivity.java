package com.example.bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int randomNumber = new Random().nextInt(50) + 1;
        Toast.makeText(MainActivity.this, Integer.toString(randomNumber) + " lines", Toast.LENGTH_SHORT).show();

        List<String> listString = new ArrayList<>();
        for (int i = 1; i <= randomNumber; i++) {
            listString.add("Item " + i);
        }

        simpleList = findViewById(R.id.lvString);
        MyAdapter adapter = new MyAdapter(listString);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ((TextView)view.findViewById(R.id.textView)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}