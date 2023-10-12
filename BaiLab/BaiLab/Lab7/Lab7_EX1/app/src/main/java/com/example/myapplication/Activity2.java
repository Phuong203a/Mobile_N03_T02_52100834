package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    EditText editFirstName;
    EditText editLastName;
    EditText editPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fN = editFirstName.getText().toString().trim();
                String lN = editLastName.getText().toString().trim();
                String p = editPhone.getText().toString().trim();
                Toast.makeText(Activity2.this, "Vui Long Dien Ten Or So Phone", Toast.LENGTH_SHORT).show();

//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\n\n\n" + fN );
//                if (TextUtils.isEmpty(fN) || TextUtils.isEmpty(p)) {
//                    Toast.makeText(Activity2.this, "Vui Long Dien Ten Or So Phone", Toast.LENGTH_SHORT).show();
//                } else {
//                    String name = lN + fN;
//                    Contact contact = new Contact(name, p);
//                    Intent intent = new Intent(Activity2.this, MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("contact", contact);
//                    startActivity(intent, bundle);
//                }
            }
        });

    }


}

//    }
//}