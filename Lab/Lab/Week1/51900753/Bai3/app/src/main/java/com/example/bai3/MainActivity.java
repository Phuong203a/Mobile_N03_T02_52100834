package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnResult, btnResult2;
    TextView tvResult, tvResult2;
    CheckBox cbWindows, cbIOS, cbAndroid;
    RadioButton rbWindows, rbIOS, rbAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnResult);
        btnResult2 = findViewById(R.id.btnResult2);
        tvResult = findViewById(R.id.tvResult);
        tvResult2 = findViewById(R.id.tvResult2);

        cbWindows = findViewById(R.id.cbWindows);
        cbAndroid = findViewById(R.id.cbAndroid);
        cbIOS = findViewById(R.id.cbIOS);

        rbWindows = findViewById(R.id.rbWindows);
        rbIOS = findViewById(R.id.rbIOS);
        rbAndroid = findViewById(R.id.rbAndroid);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                String result = "";

                if (cbWindows.isChecked()) {
                    result += cbWindows.getText().toString() + "\n";
                }
                if (cbAndroid.isChecked()) {
                    result += cbAndroid.getText().toString() + "\n";
                }
                if (cbIOS.isChecked()) {
                    result += cbIOS.getText().toString() + "\n";
                }

                tvResult.setText(result);
            }
        });

        btnResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("@*********************************************@");
                String result = "";

                if (rbWindows.isChecked()) {
                    result += cbWindows.getText().toString() + "\n";
                }
                if (rbAndroid.isChecked()) {
                    result += cbAndroid.getText().toString() + "\n";
                }
                if (rbIOS.isChecked()) {
                    result += cbIOS.getText().toString() + "\n";
                }

                tvResult2.setText(result);
            }
        });
    }
}