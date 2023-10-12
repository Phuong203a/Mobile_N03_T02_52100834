package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUs = findViewById(R.id.editTextDollar);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonConvert = findViewById(R.id.buttonConvert);
        EditText editTextEuros = findViewById(R.id.editTextEuros);
        EditText editTextVND = findViewById(R.id.editTextVND);



        buttonClear.setOnClickListener(view -> {
            editTextEuros.getText().clear();
            editTextUs.getText().clear();
            editTextVND.getText().clear();
        });

        buttonConvert.setOnClickListener(view -> {
            String dollar = editTextUs.getText().toString();
            editTextEuros.post(() -> editTextEuros.getText().clear());
            editTextVND.post(() -> editTextVND.getText().clear());
            double eurr =Double.parseDouble(dollar)*0.94;
            editTextEuros.setText(String.valueOf(eurr));
            double vnd = Double.parseDouble(dollar) * 24500;
            editTextVND.setText(String.valueOf(vnd));
        });
    }
}