package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Magnifier;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;
    EditText search;
    ListView listView;
    ArrayList<Contact> data;
    ArrayAdapter<Contact> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_ASK_PERMISSIONS);

        }
        listView = findViewById(R.id.listView);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null);



        data.clear();
        while (cursor.moveToNext()){
            String columnName = ContactsContract.Contacts.DISPLAY_NAME;
            String columnPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;

            int positionName = cursor.getColumnIndex(columnName);
            int positionPhone = cursor.getColumnIndex(columnPhone);

            String name = cursor.getString(positionName);
            String phone = cursor.getString(positionPhone);

            Contact contact = new Contact(name, phone);
            data.add(contact);
//            System.out.println(name);

        }
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String selection = ContactsContract.Contacts.DISPLAY_NAME + "like ?";
//
//                String[] queryArgs = new String[] {"%"+s+"%"};
//                Cursor cursor = getContentResolver().query(uri, null, selection, queryArgs, null);
//                System.out.println("data======================================================================================================" + data.size());
//                data.clear();
                MainActivity.this.adapter.getFilter().filter(s);
//                while (cursor.moveToNext()){
//                    String columnName = ContactsContract.Contacts.DISPLAY_NAME;
//                    int posision2 = cursor.getColumnIndex(columnName);
//                    String name = cursor.getString(posision2);
//                    data.add(name);
//                    System.out.println(name);
//
//                }
//                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
//                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.insert:

                setContentView(R.layout.insert_layout);
//                Contact contact = (Contact) getIntent().getExtras().get("contact");
//                data.add(contact);
//                listView.deferNotifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }
}

