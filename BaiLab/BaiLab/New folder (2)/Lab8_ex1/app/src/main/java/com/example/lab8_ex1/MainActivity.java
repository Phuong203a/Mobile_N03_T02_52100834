package com.example.lab8_ex1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab8_ex1.model.Student;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private CheckConnection checkConnection;
    MyAdapter adapter;
    ArrayList<Student> data;
    RecyclerView recyclerView;
    String studentSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection = new CheckConnection();
        data = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        getStudent();
        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkConnection, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(checkConnection);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolderView> {
        @NonNull
        @Override
        public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View itemView = layoutInflater.inflate(R.layout.custom_view, null, false);
            itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new MyHolderView(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
            holder.textView1.setText(data.get(position).getName());
            holder.textView2.setText(data.get(position).getEmail());
            holder.textView3.setText(data.get(position).getPhone());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    studentSelected = String.valueOf(data.get(position).getId());
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class MyHolderView extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            registerForContextMenu(itemView);
        }
    }
    public void getStudent(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.56.1/api/get-students.php").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d("onFailure", e.getMessage());
                    }
                    @Override
                    public void onResponse(Call call, final Response response)
                            throws IOException {
                        try {
                            data.clear();
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);
//                            System.out.println(json);
                            JSONArray arr = json.getJSONArray("data");
                            for(int i =0; i<arr.length(); i++) {
                                JSONObject temp = (JSONObject) arr.get(i);
                                String name = (temp.getString("name"));
                                String email = (temp.getString("email"));
                                String phone = (temp.getString("phone"));
                                int id = (temp.getInt("id"));
                                data.add(new Student(id, name, email, phone));
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        } catch (JSONException e) {
                            Log.d("onResponse", e.getMessage());
                        }
                    }
                });
    }

    public void postStudent(Student student){
        OkHttpClient client = new OkHttpClient();
        String createStudentURL = "http://192.168.56.1/api/add-student.php";
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(student.getId()))
                .add("name", student.getName())
                .add("email", student.getEmail())
                .add("phone", student.getPhone())
                .build();
        Request request = new Request.Builder()
                .url(createStudentURL)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("onFailure", e.getMessage());
            }
            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    data.add(student);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getStudent();
                        }
                    });
                } catch (JSONException e) {
                    Log.d("onResponse", e.getMessage());
                }
            }
        });
    }

    public void deleteStudent(String id){
        OkHttpClient client = new OkHttpClient();
        String createStudentURL = "http://192.168.56.1/api/delete-student.php";
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .build();
        Request request = new Request.Builder()
                .url(createStudentURL)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("onFailure", e.getMessage());
            }
            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getStudent();
                        }
                    });
                } catch (JSONException e) {
                    Log.d("onResponse", e.getMessage());
                }
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                break;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ban co muon xoa sinh vien nay?");
                builder.setMessage("Du lieu se bi xoa");
                builder.setPositiveButton("XOA",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteStudent(studentSelected);
                            }
                        });
                builder.setNegativeButton("HUY",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//write your code or do nothing.
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent,1234);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Student st = (Student) data.getSerializableExtra("student");
            postStudent(st);
            adapter.notifyDataSetChanged();
        }
    }

    class CheckConnection extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                if (!isNetworkAvailable(context)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("INTERNET");
                    builder.setMessage("invalid");
                    AlertDialog dialog = builder.create();
                    dialog.show();
//                Toast.makeText(context, "INTERNET INVALID", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "INTERNET VALID", Toast.LENGTH_SHORT).show();
                    getStudent();
                    adapter.notifyDataSetChanged();
                }
            }
        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager == null){
                return false;
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                Network network = connectivityManager.getActiveNetwork();
                if(network == null)
                    return false;
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
                return capabilities!=null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            }else{
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo!=null&&networkInfo.isConnected();
            }
        }
    }

}