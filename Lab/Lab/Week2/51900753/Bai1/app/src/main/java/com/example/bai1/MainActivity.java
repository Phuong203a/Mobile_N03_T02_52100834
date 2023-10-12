package com.example.bai1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_EMAIL_DATA = "KEY_EMAIL_DATA";
    public static final int REQUEST_FROM_MAIN = 123456;

    EditText etEmail;
    Button btnLogin;
    TextView tvGoodBye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoodBye = findViewById(R.id.tvGoodBye);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra(KEY_EMAIL_DATA, etEmail.getText().toString());
                System.out.println("click roi @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                startActivityForResult(intent, REQUEST_FROM_MAIN);
                break;
            }
            default: break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FROM_MAIN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                btnLogin.setVisibility(View.GONE);
                tvGoodBye.setText("Hẹn gặp lại");
                etEmail.setText(data.getStringExtra(MainActivity2.KEY_NAME_DATA));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("asd", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("asd", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("asd", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("asd", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("asd", "onDestroy");

    }


}