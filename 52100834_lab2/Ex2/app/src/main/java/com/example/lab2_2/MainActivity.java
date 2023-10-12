package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtViewFollowing;
    TextView txtViewFollower;
    Button btnFollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewFollowing = findViewById(R.id.txtViewFollowing);
        txtViewFollower = findViewById(R.id.txtViewFollower);
        btnFollow = findViewById(R.id.btnFollow);
        int following = new Random().nextInt(10000)+100;
        int follower = new Random().nextInt(10000)+100;
        txtViewFollowing.setText(following+"\nFollowing");
        txtViewFollower.setText(follower+"\nFollower");

        btnFollow.setOnClickListener(new View.OnClickListener() {
//            boolean checkClick = true;
            @Override
            public void onClick(View view) {
               if("follow".equals(btnFollow.getText().toString().toLowerCase())){
                    txtViewFollower.setText(follower+1+"\nFollower");
                    btnFollow.setText("Unfollow");
               }else {
                    txtViewFollower.setText((follower)+"\nFollower");
                    btnFollow.setText("Follow");
               }
            }
        });
    }
}