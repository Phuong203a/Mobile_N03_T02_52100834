package com.example.lab6_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_Back;
    ListView listView;
    ArrayList<String> data;
    File[] filesAndFolders;
    File root;
    String path;

    ArrayList<Boolean> check;

    ArrayAdapter<String> adapter;
    ArrayList<String> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Back = findViewById(R.id.btn_back);
        data = new ArrayList<>();
        path = Environment.getExternalStorageDirectory().getPath();
        root = new File(path);
        filesAndFolders =root.listFiles();

        for(File file : filesAndFolders){
            data.add(file.getName());
            check.add(false);
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.my_layout,
                R.id.textView,
                data){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckBox checkBox = view.findViewById(R.id.checkBox);
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        check.add(position, !check.get(position));
                    }
                });
                return view;
            }
        };

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    private class MyAdapter extends BaseAdapter {
        private String[] filesAndFolders;
        private int layout;
        private Context context;
        public MyAdapter(String[] files, int layout, Context context) {
            this.filesAndFolders = files;
            this.layout = layout;
            this.context = context;
        }
        @Override
        public int getCount() {
            return filesAndFolders.length;
        }

        @Override
        public Object getItem(int i) {
            return filesAndFolders[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(layout, null);
            TextView textView =view.findViewById(R.id.textView);
            textView.setText(filesAndFolders[i]);
            return view;
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else
            return false;

    }

    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "Storage Permission Is Require. Please Allow From Seeting", Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
    }
}