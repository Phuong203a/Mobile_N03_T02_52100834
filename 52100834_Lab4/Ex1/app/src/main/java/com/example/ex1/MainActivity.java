package com.example.ex1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        dataList = generateRandomData(20); // Số lượng phần tử ngẫu nhiên

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = dataList.get(position);
                Toast.makeText(MainActivity.this, "click item " + itemValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm tạo dữ liệu ngẫu nhiên
    private List<String> generateRandomData(int count) {
        List<String> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < count; i++) {
            data.add("item " + i);
        }
        return data;
    }
}