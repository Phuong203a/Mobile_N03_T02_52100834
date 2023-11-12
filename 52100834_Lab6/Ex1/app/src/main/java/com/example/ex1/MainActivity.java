package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
        private static final String PREFS_NAME = "MyPrefsFile";
        private static final String TEXT_COLOR_KEY = "textColor";
        private static final String BACKGROUND_COLOR_KEY = "backgroundColor";
        private static final String COUNT_KEY = "count";

        private EditText editTextColor;
        private EditText editBackgroundColor;
        private Button btnSave;
        private TextView textViewCount;
        private int count = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editTextColor = findViewById(R.id.editTextColor);
            editBackgroundColor = findViewById(R.id.editBackgroundColor);
            btnSave = findViewById(R.id.btnSave);
            textViewCount = findViewById(R.id.textViewCount);

            // Load count and colors from SharedPreferences
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            count = preferences.getInt(COUNT_KEY, 0);
            int textColor = preferences.getInt(TEXT_COLOR_KEY, Color.BLACK);
            int backgroundColor = preferences.getInt(BACKGROUND_COLOR_KEY, Color.WHITE);

            // Set the text and background colors
            textViewCount.setTextColor(textColor);
            textViewCount.setBackgroundColor(backgroundColor);

            updateCount();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save text and background colors
                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putInt(TEXT_COLOR_KEY, Color.parseColor(editTextColor.getText().toString()));
                    editor.putInt(BACKGROUND_COLOR_KEY, Color.parseColor(editBackgroundColor.getText().toString()));
                    editor.apply();
                }
            });
        }

        private void updateCount() {
            count++;
            textViewCount.setText("Count: " + count);
            // Save the updated count
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt(COUNT_KEY, count);
            editor.apply();
        }
    }
