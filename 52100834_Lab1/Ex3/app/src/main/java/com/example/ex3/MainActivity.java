package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editTextBill);
        TextView percentageTxt = findViewById(R.id.textViewPercent);
        TextView tipTxt = findViewById(R.id.textViewTip);
        TextView totalTxt = findViewById(R.id.textViewTotal);
        Button btnSub = findViewById(R.id.buttonSub);
        Button btnPlus = findViewById(R.id.buttonPlus);

        btnSub.setOnClickListener(view -> {
            int percentage = Integer.parseInt(String.valueOf(percentageTxt.getText()).trim().replace("%", ""));
            if (percentage > 0) {
                percentage -= 1;
                percentageTxt.setText(percentage + "%");
            }
        });

        btnPlus.setOnClickListener(view -> {
            int percentage = Integer.parseInt(String.valueOf(percentageTxt.getText()).trim().replace("%", ""));
                percentage += 1;
                percentageTxt.setText(percentage + "%");
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate(percentageTxt,tipTxt,totalTxt,editText);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        }catch (Exception ex){
            Log.d("Error onCreate",ex.getMessage());
        }

    }
    private void calculate(TextView percentageTxt,TextView tipTxt,TextView totalTxt,EditText editText ){
        int percentage = Integer.parseInt(String.valueOf(percentageTxt.getText()).trim().replace("%", ""));
        double bill = Double.parseDouble(editText.getText().toString());
        double tip = bill * percentage / 100;
        double total = bill + tip;
        tipTxt.setText(String.valueOf(tip));
        totalTxt.setText(String.valueOf(total));
    }
}