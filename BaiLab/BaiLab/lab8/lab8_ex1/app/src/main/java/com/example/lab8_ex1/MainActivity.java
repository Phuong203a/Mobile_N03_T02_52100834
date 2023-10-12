package com.example.lab8_ex1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    MyAdapter adapter;
    ArrayList<Student> data;
    RecyclerView rclView;
    String selected = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        rclView = findViewById(R.id.rclView);
        rclView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();


        rclView.setAdapter(adapter);
        getData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(intent,1234);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void getData() {
        data.clear();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://10.41.11.222/api/getstudents.php").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("onFailure", e.getMessage());
            }
            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
//                String resString = response.body().string();
                try {
                    data.clear();
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
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
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    Log.d("onResponse", e.getMessage());
                }
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.student_layout,null, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tvName.setText(data.get(position).getName());
            holder.tvEmail.setText(data.get(position).getEmail());
            holder.tvPhone.setText(data.get(position).getPhone());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected = String.valueOf(data.get(position).getId());
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.nameStudent);
            tvEmail = itemView.findViewById(R.id.emailStudent);
            tvPhone = itemView.findViewById(R.id.phoneStudent);
            registerForContextMenu(itemView);
        }
    }
}