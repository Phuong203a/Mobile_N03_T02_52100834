package com.example.bai1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_NAME_DATA = "KEY_NAME_DATA";

    TextView tvGreet;
    EditText etName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvGreet = findViewById(R.id.tvGreet);
        etName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.KEY_EMAIL_DATA);
        tvGreet.setText("Xin chào " + username + ", vui lòng nhập tên");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave: {
                Intent intent = new Intent();
                intent.putExtra(KEY_NAME_DATA, etName.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }


}