package com.example.bai2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnOpenBrowser;
    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenBrowser = findViewById(R.id.btnOpenBrowser);
        etUrl = findViewById(R.id.etUrl);
    }

    public void openBrowser(View v) {
//        Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show();
        String url = etUrl.getText().toString();

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "https://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}