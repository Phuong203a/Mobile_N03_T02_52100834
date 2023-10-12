package com.example.lab6_ex4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.TestOnly;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    File[] files;
    File root;
    ListView listView;
    ArrayList<String> data, temp;
    ArrayList<Boolean> index;
    String path;
    Button btnBack;
    EditText folderName, fileName, contentFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = new ArrayList<>();
        btnBack = findViewById(R.id.btnBack);
        temp = new ArrayList<>();
        //back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        path = Environment.getExternalStorageDirectory().getPath();
        root = new File(path);
        files = root.listFiles();
        data = new ArrayList<>();
        for(File f:files) {
            data.add(f.getName());
            index.add(false);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_custom, R.id.textView,data){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckBox checkBox = view.findViewById(R.id.checkBox);
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        index.add(position, !index.get(position));
                    }
                });
                return view;
            }
        };
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.textView);
                String name = textView.getText().toString();
                temp.add(name);
                path = path + "/" + name;
                root = new File(path);
                files = root.listFiles();
               if(files != null){
                    files = root.listFiles();
                    data.clear();
                    for(File x: files)
                        data.add(x.getName());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp.size() != 0) {
                    path = path.replace("/" + temp.get(temp.size() - 1), "");
                    temp.remove(temp.size() - 1);
                    root = new File(path);
                    files = root.listFiles();
                    data.clear();
                    index.clear();
                    for (File x : files) {
                        data.add(x.getName());
                        index.add(false);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addFolder:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setView(R.layout.add_folder_dialog);
                View view = getLayoutInflater().inflate(R.layout.add_folder_dialog, null);
                folderName = view.findViewById(R.id.folderName);
                builder.setView(view);
                System.out.println("OK");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String folder = folderName.getText().toString();
                                System.out.println(path+"/"+folder);
                                File dir = new File(path+"/"+folder);
                                if(dir.mkdir()){
                                    root = new File(path);
                                    files = root.listFiles();
                                    if(files != null){
                                        files = root.listFiles();
                                        data.clear();
                                        index.clear();
                                        for(File x: files) {
                                            data.add(x.getName());
                                            index.add(false);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                    System.out.println("CREATED");
                                } else System.out.println("FAILED");
                            }
                        });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.addFile:
                AlertDialog.Builder builderAddFile = new AlertDialog.Builder(this);
//                builder.setView(R.layout.add_folder_dialog);
                View viewAddFile = getLayoutInflater().inflate(R.layout.add_file_dialog, null);
                fileName = viewAddFile.findViewById(R.id.fileName);
                contentFile = viewAddFile.findViewById(R.id.contentFile);
                builderAddFile.setView(viewAddFile);
                System.out.println("OK");
                builderAddFile.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(checkPermission()){
                            String file = fileName.getText().toString();
                            String content = contentFile.getText().toString();
                            String pathFile = path+"/"+file+".txt";
                            System.out.println(pathFile);
                            File newFile = new File(pathFile);
                            try {
                                if(newFile.createNewFile()){
                                    FileWriter fw = new FileWriter(pathFile);
                                    fw.write(content);
                                    fw.close();
                                    System.out.println("CREATED FILE");
                                }
                                else System.out.println("FAILED");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            requestPermission();
                        }
                    }
                });

                builderAddFile.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialogaddFile = builderAddFile.create();
                dialogaddFile.show();
                break;
            case R.id.remove:
                AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
                builderDelete.setTitle("Do you want to delete those files?");
                builderDelete.setMessage("You'll lost all your data");
                builderDelete.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i = index.size()-1; i>=0; i--){
                                    if(index.get(i)){
                                        data.remove(i);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
                builderDelete.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//write your code or do nothing.
                            }
                        });
                AlertDialog dialogDelete = builderDelete.create();
                dialogDelete.show();
                break;
            case R.id.removeAll:
                AlertDialog.Builder builderDeleteAll = new AlertDialog.Builder(this);
                builderDeleteAll.setTitle("Do you want to delete all?");
                builderDeleteAll.setMessage("You'll lost all your data");
                builderDeleteAll.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.clear();
                                index.clear();
                                adapter.notifyDataSetChanged();
                            }
                        });
                builderDeleteAll.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//write your code or do nothing.
                            }
                        });
                AlertDialog dialogDeleteAll = builderDeleteAll.create();
                dialogDeleteAll.show();
                break;
        }
        return true;
    }

    private class MyAdapter extends BaseAdapter{
        private String[] files;
        private int layout;
        private Context context;

        public MyAdapter(String[] files, int layout, Context context) {
            this.files = files;
            this.layout = layout;
            this.context = context;
        }
        @Override
        public int getCount() {
            return files.length;
        }

        @Override
        public Object getItem(int i) {
            return files[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(layout, null);
            TextView textView = view.findViewById(R.id.textView);
            textView.setText(files[i]);

            return view;
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else return false;
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "Store permission is requires. Please allow from settings", Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
        }
    }
}