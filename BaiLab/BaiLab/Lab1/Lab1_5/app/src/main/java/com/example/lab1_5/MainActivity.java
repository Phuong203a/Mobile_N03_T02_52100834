package com.example.lab1_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBoxAndroid = findViewById(R.id.checkBoxAndroid);
        CheckBox checkBoxIos = findViewById(R.id.checkBoxIos);
        CheckBox checkBoxWindows = findViewById(R.id.checkBoxWindows);
        CheckBox checkBoxRim = findViewById(R.id.checkBoxRim);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(view->{
            boolean isAndroid = checkBoxAndroid.isChecked();
            boolean isIos = checkBoxIos.isChecked();
            boolean isWindows = checkBoxWindows.isChecked();
            boolean isRim = checkBoxRim.isChecked();

            TextView textView = findViewById(R.id.textResult);
            String result = "The following was selected ...";
            result += "\nAndroid: "+isAndroid;
            result += "\nIos: "+isIos;
            result += "\nWindows: "+isWindows;
            result += "\nRim: "+isRim;

            textView.setText(result);
        });



    }
}