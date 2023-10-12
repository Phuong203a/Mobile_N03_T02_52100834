package tdtu.lab04.exam05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv);
        Button add = findViewById(R.id.btnAdd);
        Button remove = findViewById(R.id.btnREMOVE);

        List<User> list = new ArrayList<User>();
        adapter = new CustomAdapter(MainActivity.this, R.layout.activity_main, list);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int startIndex = list.size();
                int toIndex = startIndex + 5;
                for (int i = startIndex; i < toIndex; i++) {
                    int index = i+1;
                    String userName = "User " + index;
                    String userEmail = "user" + index + "@tdtu.edu.vn";
                    User newUser = new User(userName, userEmail);
                    list.add(newUser);
                }
                adapter.notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() == 0)
                {
                    Toast.makeText(MainActivity.this, "List of users is empty", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < 5; i++) {
                    list.remove(list.size() - 1);
                }
                adapter.notifyDataSetChanged();
            }
        });



    }



}
