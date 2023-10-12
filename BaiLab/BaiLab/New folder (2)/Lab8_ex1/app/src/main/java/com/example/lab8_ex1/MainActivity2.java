package com.example.lab8_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.lab8_ex1.model.Student;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    TextInputLayout id, name, email, phone;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = (TextInputLayout) findViewById(R.id.textLayoutID);
        name = (TextInputLayout) findViewById(R.id.textLayoutName);
        email = (TextInputLayout) findViewById(R.id.textLayoutEmail);
        phone = (TextInputLayout) findViewById(R.id.textLayoutPhone);
        save = findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validator()){
                    int ID = Integer.parseInt(id.getEditText().getText().toString());
                    String NAME = name.getEditText().getText().toString();
                    String EMAIL = email.getEditText().getText().toString();
                    String PHONE = phone.getEditText().getText().toString();
                    Student st = new Student(ID,NAME,EMAIL,PHONE);
                    Intent intent = new Intent();
                    intent.putExtra("student", st);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    private boolean validator(){
        boolean temp = false;
        String NAME = name.getEditText().getText().toString();
        String EMAIL = email.getEditText().getText().toString();
        String PHONE = phone.getEditText().getText().toString();
        if(id.getEditText().getText().toString().length() == 0 || ! id.getEditText().getText().toString().matches("-?\\d+(\\.\\d+)?")){
            id.getEditText().requestFocus();
            id.getEditText().setError("Vui long nhap lai ID");
        }
        else if(NAME.length() == 0 || !NAME.matches("[a-zA-Z ]+")) {
            name.getEditText().requestFocus();
            name.getEditText().setError("Vui long nhap ten");
        }
        else if(EMAIL.length() == 0 || !EMAIL.matches("^(.+)@(\\S+)$")){
            email.getEditText().requestFocus();
            email.getEditText().setError("Vui long kiem tra lai email");
        }
        else if(PHONE.length() < 10){
            phone.getEditText().requestFocus();
            phone.getEditText().setError("Vui long nhap dung so dien thoai");
        }
        else
            temp = true;
        return temp;
    }
}