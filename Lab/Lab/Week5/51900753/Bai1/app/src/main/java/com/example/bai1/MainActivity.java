package com.example.bai1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRV;
    List<Item> itemList = new ArrayList<>();
    MyAdapter adapter;
    AlertDialog.Builder dialogBox;
    int dialogAction;
    final int ACTION_DELETE_ALL = 1;
    final int ACTION_DELETE_SELECTED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int n = new Random().nextInt(10) + 30;

        for (int i = 1; i <= n; i++) {
            itemList.add(new Item("Person" + i, false));
        }

        adapter = new MyAdapter(itemList);
        rvRV = findViewById(R.id.rvRV);
        rvRV.setAdapter(adapter);
        rvRV.setLayoutManager(new LinearLayoutManager(this));

        dialogBox = new AlertDialog.Builder(this);
        dialogBox.setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_all: {
                for (int i = adapter.itemList.size() - 1; i >= 0 ; i--) {
                    adapter.itemList.get(i).checked = true;
                }
                adapter.notifyDataSetChanged();
                break;
            }

            case R.id.uncheck_all: {
                for (int i = adapter.itemList.size() - 1; i >= 0 ; i--) {
                    adapter.itemList.get(i).checked = false;
                }
                adapter.notifyDataSetChanged();
                break;
            }

            case R.id.delete_all: {
                dialogAction = ACTION_DELETE_ALL;
                dialogBox.setMessage("Do you want to delete all?").show();
                break;
            }

            case R.id.delete_selected: {
                dialogAction = ACTION_DELETE_SELECTED;
                dialogBox.setMessage("Do you want to delete selected items?").show();
            }
        }

        return true;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                switch (dialogAction) {
                    case ACTION_DELETE_ALL: {
                        int count = adapter.itemList.size();
                        adapter.itemList.clear();
                        adapter.notifyItemRangeRemoved(0, count);
                        break;
                    }

                    case ACTION_DELETE_SELECTED: {
                        for (int i = adapter.itemList.size() - 1; i >= 0 ; i--) {
                            if (adapter.itemList.get(i).checked) {
                                adapter.itemList.remove(i);
                                adapter.notifyItemRemoved(i);
                            }
                        }
                        break;
                    }
                }
            }
        }
    };
}