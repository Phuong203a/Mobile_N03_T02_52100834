package com.example.bai1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SimpleCursorAdapter simpleCursorAdapter;
    ListView listView;
    ArrayList<Contact> contactList;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lvListView);

        //read contacts permission
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            contactList = getContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
        }

        contactAdapter = new ContactAdapter(contactList);
        listView.setAdapter(contactAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactAdapter.listContact.get(i).getNumber()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do your things
                    contactList = getContacts();
                } else {
                    // permission denied, boo!
                    Toast.makeText(this, "permission to read contacts is denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
            EditText etName = dialogView.findViewById(R.id.etName);
            EditText etNumber = dialogView.findViewById(R.id.etNumer);

            dialog.setView(dialogView);

            //##################################### ADD NEW CONTACT ##########################################################
            dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS) !=
                            PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{android.Manifest.permission.WRITE_CONTACTS},
                                2);
                    }

                    if (etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Hay nhap du thong tin", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Uri uri = getContentResolver()
                            .insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());

                    long id = ContentUris.parseId(uri);

                    ContentValues nameValues = new ContentValues();
                    nameValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                    nameValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, etName.getText().toString().trim());
                    nameValues.put(ContactsContract.Data.RAW_CONTACT_ID, id);
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, nameValues);

                    ContentValues numberValues = new ContentValues();
                    numberValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    numberValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, etNumber.getText().toString().trim());
                    numberValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    numberValues.put(ContactsContract.Data.RAW_CONTACT_ID, id);
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, numberValues);

                    Contact newContact = new Contact(etName.getText().toString(), etNumber.getText().toString());
                    contactAdapter.listContact.add(newContact);
                    contactAdapter.notifyDataSetChanged();
                }
            });

            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("Range")
    public ArrayList getContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();

        //get all contacts with cursor
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        //loop through contacts in cursor
        while (cursor.moveToNext()) {
            String contactId;
            String name;
            String phoneNumber = "";

            contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            //get all phone numbers of current contactID
            Cursor phoneCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId},
                    null);

            //in this case only get ONE phone number for simplicity
            if (phoneCursor.moveToNext()) {
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phoneCursor.close();

            //add new contact to ArrayList
            Contact contact = new Contact(name, phoneNumber);
            contactList.add(contact);
        }

        cursor.close();
        return contactList;
    }
}