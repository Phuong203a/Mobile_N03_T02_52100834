package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpen =findViewById(R.id.btnOpen);
        EditText url =findViewById(R.id.url);

        View.OnClickListener openOnClickListener = v -> {
            String urlValue =url.getText().toString();
            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlValue));
            startActivity(urlIntent);
        };

        btnOpen.setOnClickListener(openOnClickListener);
    }
}