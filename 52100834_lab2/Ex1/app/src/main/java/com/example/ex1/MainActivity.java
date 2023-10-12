package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            EditText editTextPersonname= findViewById(R.id.editTextTextPersonName);
            EditText editTextPassword = findViewById(R.id.editTextTextPassword);
            Button button =  findViewById(R.id.button);

            button.setOnClickListener(v -> {
                if(isValidPassword(editTextPassword.getText().toString())){
                    Toast.makeText(MainActivity.this, "Password length must >6 and must contains both upppercase and lowercase", Toast.LENGTH_LONG).show();
                }
                else if(editTextPersonname.getText().toString().equals("")&& editTextPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "You need username and password", Toast.LENGTH_LONG).show();
                }
                else if(editTextPersonname.getText().toString().equals("admin")&& editTextPassword.getText().toString().equals("admin1234")){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Username or passord is not correct", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }



//        CharSequence editTextTextPersonName = "";
//        CharSequence editTextTextPassword = "";
//        int duration = Toast.LENGTH_LONG;
//        Toast toast = Toast.makeText(this,"You need username and password",Toast.LENGTH_LONG);
//        toast.show();


    }
    private boolean isValidPassword(String str) {
        if(str.length()<6){
            return false;
        }
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }
}