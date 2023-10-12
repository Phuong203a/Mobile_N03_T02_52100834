package com.example.bai5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();
    UserAdapter adapter;
    ListView listView;
    Button btnAdd;
    Button btnRemove;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        tvTotal = findViewById(R.id.tvTotal);
        listView = findViewById(R.id.lvListView);


        adapter = new UserAdapter(userList);
        tvTotal.setText(Integer.toString(adapter.userList.size()));
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = adapter.userList.size();
                for (int i = 1; i <= 5; i++) {
                    adapter.userList.add(new User("User " + (size + i), "user" + (size + i) + "@tdtu.edu.vn"));
                }
                adapter.notifyDataSetChanged();
                tvTotal.setText(Integer.toString(adapter.userList.size()));
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = adapter.userList.size();

                if (size < 5) {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 1; i <= 5; i++) {
                    adapter.userList.remove(size - i);
                }
                adapter.notifyDataSetChanged();
                tvTotal.setText(Integer.toString(adapter.userList.size()));
            }
        });
    }
}