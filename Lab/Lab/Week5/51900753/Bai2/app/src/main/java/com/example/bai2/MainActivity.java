package com.example.bai2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRV;
    List<Item> itemList = new ArrayList<>();
    MyAdapter adapter;
    AlertDialog.Builder dialogBox;
    Switch actionBarSwitchView;

    int dialogAction;
    final int ACTION_DELETE_ALL = 1;
    final int ACTION_DELETE_ITEM = 2;
    final int REQUEST_FROM_MAIN = 51900753;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= 5; i++) {
            itemList.add(new Item("Event" + i, "Place" + i, calendar));
        }

        adapter = new MyAdapter(itemList);

        rvRV = findViewById(R.id.rvRV);
        rvRV.setAdapter(adapter);
        rvRV.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(rvRV);

        dialogBox = new AlertDialog.Builder(this);
        dialogBox.setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        actionBarSwitchView = (Switch) (menu.findItem(R.id.menu_switch).getActionView());
        actionBarSwitchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.isShowAll = isChecked;
                adapter.notifyDataSetChanged();
            }
        });
//        actionBarSwitchView.setChecked(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                break;

            case R.id.menu_rm_all:
                dialogAction = ACTION_DELETE_ALL;
                dialogBox.setMessage("Are you sure to delete all events?").show();
                break;

            case R.id.menu_add:
                Intent intent = new Intent(this, AddEventActivity.class);
                startActivityForResult(intent, REQUEST_FROM_MAIN);
                break;

            default: break;
        }

        return true;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //Click "yes"
            if (which == DialogInterface.BUTTON_POSITIVE) {
                switch (dialogAction) {

                    case ACTION_DELETE_ALL: {
                        int count = adapter.itemList.size();
                        adapter.itemList.clear();
                        adapter.notifyItemRangeRemoved(0, count);
                        break;
                    }

                    case ACTION_DELETE_ITEM: {
                        adapter.itemList.remove(adapter.positionToBeDeleted);
                        adapter.notifyItemRemoved(adapter.positionToBeDeleted);
                        break;
                    }
                }
            }
            //else: nothing
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, ACTION_DELETE_ITEM, 0, "Delete");
        menu.add(0, 123, 0, "Edit");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == ACTION_DELETE_ITEM) {
            dialogAction = ACTION_DELETE_ITEM;
            dialogBox.setMessage("Do you want to delete " + adapter.itemList.get(adapter.positionToBeDeleted).getName() + "?").show();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FROM_MAIN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Item newItem = (Item) data.getSerializableExtra(AddEventActivity.OBJECT_ITEM);
                adapter.itemList.add(newItem);
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        }
    }
}