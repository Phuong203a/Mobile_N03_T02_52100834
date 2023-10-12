package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOpen =findViewById(R.id.button);
        EditText url =findViewById(R.id.url);

        View.OnClickListener openOnClickListener = v -> {
            String urlValue =url.getText().toString();
            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            intent.putExtra("URL", urlValue);
            // start the Intent
            startActivity(intent);
        };

        btnOpen.setOnClickListener(openOnClickListener);

    }

}