package com.example.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkBox;
    Button btnReset;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkBox = findViewById(R.id.checkBox);
        btnReset = findViewById(R.id.btnReset);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUserName.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin1234")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
                if(edtPassword.getText().toString().equals(edtPassword.getText().toString().toLowerCase())
                        || edtPassword.getText().toString().equals(edtPassword.getText().toString().toUpperCase())
                        || edtUserName.getText().toString().length() < 6){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập username hoặc password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUserName.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập username", Toast.LENGTH_SHORT).show();
                }
                if(edtUserName.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Reset mật khẩu thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
    }