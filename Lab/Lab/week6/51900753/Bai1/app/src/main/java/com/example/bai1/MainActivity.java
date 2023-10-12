package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("MySP", MODE_PRIVATE);

        //load
        int value = sharedPref.getInt("COUNTER", 0);

        //save
        sharedPref.edit()
                .putInt("COUNTER", ++value)
                .apply();

        TextView counter = findViewById(R.id.counter);
        counter.setText(String.valueOf(value));
    }
}