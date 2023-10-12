package com.example.lab1_5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup =findViewById(R.id.RadioGroup);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(view->{
            int buttonId = radioGroup.getCheckedRadioButtonId();
            TextView textView = findViewById(R.id.txtResult);
            RadioButton radioButton = findViewById(buttonId);
            textView.setText("You choose: "+radioButton.getText());
        });



    }
}