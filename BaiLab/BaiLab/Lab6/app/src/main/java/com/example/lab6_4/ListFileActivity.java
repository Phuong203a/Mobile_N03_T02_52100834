package com.example.lab6_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ListFileActivity extends AppCompatActivity {

    ListView listView;
//    TextView nofiles_textView;

    ArrayAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_file);

        listView = findViewById(R.id.listView);
//        nofiles_textView = findViewById(R.id.nofiles_textView);
//        String path = getIntent().getStringExtra("path");
        Intent intent = new Intent();
        String path = intent.getStringExtra("path");
        File root = new File(path);

        File[] filesAndFolders = root.listFiles();


        ArrayList<String> data = new ArrayList<>();
        for(File f : filesAndFolders){
            data.add(f.getName());
        }
        adapter = new ArrayAdapter<String>(this, R.layout.my_layout, R.id.file_name_textView,data);
//        if(filesAndFolders == null || filesAndFolders.length == 0){
//            nofiles_textView.setVisibility(View.VISIBLE);
//            return;
//        }
//        nofiles_textView.setVisibility(View.INVISIBLE);
        listView.setAdapter(adapter);
    }
}