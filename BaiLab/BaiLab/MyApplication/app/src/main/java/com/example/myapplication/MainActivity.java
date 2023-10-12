package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    EditText editColor;
    EditText editBackround;
    Button button;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        editColor = findViewById(R.id.editColor);
        editBackround = findViewById(R.id.editBackround);
        button = findViewById(R.id.button);

        SharedPreferences sprefs = getSharedPreferences("my_preferred", Activity.MODE_PRIVATE);
        String txtcolor = sprefs.getString("color", "#FFFFFF");
        String txtBgColor = sprefs.getString("bgColor", "#2222FF");
        count = sprefs.getInt("count", 1);
        textView1.setText(count + "");

        textView1.setTextColor(Color.parseColor(txtcolor));
        textView1.setBackgroundColor(Color.parseColor(txtBgColor));

        editColor.setText(txtcolor);
        editBackround.setText(txtBgColor);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String txtcolor = editColor.getText().toString();
//                String txtBgColor = editBackround.getText().toString();
//                textView1.setTextColor(Color.parseColor(txtcolor));
////                textView1.setBackground(Color.parseColor(txtBgColor));
//                textView1.setBackground(Color.parseColor(txtBgColor));

                SharedPreferences myPrefs = getSharedPreferences("my_preferred", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("color", editColor.getText().toString());
                editor.putString("bgColor", editBackround.getText().toString());

                editor.apply();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        count++;
        SharedPreferences myPrefs = getSharedPreferences("my_preferred", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("color", editColor.getText().toString());
        editor.putString("bgColor", editBackround.getText().toString());
        editor.putInt("count", count);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}