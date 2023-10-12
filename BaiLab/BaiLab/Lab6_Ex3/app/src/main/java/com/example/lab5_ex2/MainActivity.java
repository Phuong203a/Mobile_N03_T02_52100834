package com.example.lab5_ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SuKien> listData;

    SuKienBaseAdapter adapter;
    ListView listView;
    MySQLiteHelper db;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = new ArrayList<>();
        db = new MySQLiteHelper(this);
//        db.addSuKien(new SuKien("Sinh hoat chu nhiem", "C120","09/03/2023","04:43", "true"));
        listData = db.getAllSuKien();
        listView = findViewById(R.id.listView);

        adapter = new SuKienBaseAdapter(this,R.layout.custom_view,listData)
        {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                System.out.println(i);
                View view1 = super.getView(i, view, viewGroup);
                if(listData.get(i).getTrangthai().equals("true")) {
                    registerForContextMenu(view1);
                }
                else {
                    unregisterForContextMenu(view1);}
                Switch aSwitch1 = view1.findViewById(R.id.app_bar_switch1);
                aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        TextView name = view1.findViewById(R.id.name);
                        TextView time = view1.findViewById(R.id.time);
                        TextView room = view1.findViewById(R.id.room);
                        if(b) {
                            name.setTextColor(Color.WHITE);
                            room.setTextColor(Color.WHITE);
                            time.setTextColor(Color.WHITE);
                            listData.get(i).setTrangthai(String.valueOf(b));
                            registerForContextMenu(view1);
                        }
                        else {
                            name.setTextColor(Color.GRAY);
                            room.setTextColor(Color.GRAY);
                            time.setTextColor(Color.GRAY);
                            listData.get(i).setTrangthai(String.valueOf(b));
                            unregisterForContextMenu(view1);}
                    }
                });
                return view1;
            };
        };
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNew:
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 123);
                break;
            case R.id.about:
                break;
            case R.id.removeAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure to remove all events");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteAll();
                        listData.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
//        aSwitch = findViewById(R.id.switchForActionBar);
//        aSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                check = !check;
//               if(!check){
//                   for(int i = listData.size()-1; i>=0; i--){
//                       if(!listData.get(i).getTrangthai().equals("false"))
//                           listData.remove(i);
//                   }
//                   adapter.notifyDataSetChanged();
//               } else{
//                   listData = (ArrayList<SuKien>) temp.clone();
//                   adapter.notifyDataSetChanged();
//               }
//            }
//        });
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SuKien suKien = (SuKien) data.getSerializableExtra("sukien");
        System.out.println(suKien);
        db.addSuKien(suKien);
        listData.add(suKien);
        adapter.notifyDataSetChanged();
    }
}