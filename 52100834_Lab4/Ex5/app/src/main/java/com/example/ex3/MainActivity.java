package com.example.ex3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.time.Duration;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Button addMoreButton;
    private Button removeSelectedButton;
    private TextView textCount;
    private Stack<User> userList = new Stack<>();
    private int NUMBER_USER_ACTION = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMoreButton = findViewById(R.id.buttonAddMore);
        removeSelectedButton = findViewById(R.id.btnRemove);
        textCount=findViewById(R.id.textCount);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(userList);
        recyclerView.setAdapter(adapter);

        addMoreButton.setOnClickListener(view -> {
            for(int i = 0 ; i < NUMBER_USER_ACTION ; i++){
                userList.add(generateUser(userList));
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(userList.size()-1);
            }
            textCount.setText("Total users: "+userList.size());
        });
        removeSelectedButton.setOnClickListener(view -> {
            if(userList.isEmpty()){
                Toast.makeText(this.getApplicationContext(),"List of users is empty",Toast.LENGTH_SHORT).show();
                return;
            }
            for(int i = 0 ; i < NUMBER_USER_ACTION ; i++){
                userList.pop();
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(userList.size()-1);
            }
            textCount.setText("Total users: "+userList.size());
        });
    }

    private User generateUser (Stack<User> userList){
        User user = new User();
        user.name= "User "+ (userList.size()+1);
        user.email= "user"+ (userList.size()+1)+"@tdtu.edu.vn";
        return user;
    }

}


