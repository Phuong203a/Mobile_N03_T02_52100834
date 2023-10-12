package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView txtview1 = findViewById(R.id.txtView1);
        EditText editTextName = findViewById(R.id.editTextName);
        Button btnSaveExit = findViewById(R.id.btnSaveExit);

        Intent intent = getIntent();

        String email = intent.getStringExtra("Email");
        txtview1.setText("Xin chào " + email);
        btnSaveExit.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("name", editTextName.getText().toString());
            setResult(1,intent2);
        });
    }
}