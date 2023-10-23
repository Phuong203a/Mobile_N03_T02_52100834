package com.example.lab5_ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private List<Phone> phoneList;
    private boolean isCheck=false;
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneList = generatePhone();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(phoneList);
        recyclerView.setAdapter(adapter);
    }

    private List<Phone> generatePhone() {
        List<Phone> phoneList = new ArrayList<>();
        Random r = new Random();
        int num = r.nextInt(15);
        Phone phone;
        for (int i = 0; i < num; i++) {
            phone = new Phone();
            phone.id = i;
            phone.name = "Mobile " + i;
            phoneList.add(phone);
        }
        return phoneList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemCheck){
            checkAll();
            return true;
        } else if(item.getItemId()==R.id.itemDeleteAll){
            deleteAll();
            return true;
        } else if(item.getItemId()==R.id.itemDeleteSelect){
            deleteSelected();
            return true;
        }
                return super.onOptionsItemSelected(item);
    }

    private void deleteAll(){
        phoneList.clear();
        adapter.notifyDataSetChanged();
    }
    private void deleteSelected(){
                    List<Phone> itemsToRemove = new ArrayList<>();
            for (Phone phone : phoneList) {
                if (phone.isChecked) {
                    itemsToRemove.add(phone);
                }
            }
            phoneList.removeAll(itemsToRemove);
            adapter.notifyDataSetChanged();
    }
    private void checkAll(){
        phoneList.forEach(phone -> phone.isChecked=!isCheck);
        isCheck=!isCheck;
        adapter.notifyDataSetChanged();
    }
}