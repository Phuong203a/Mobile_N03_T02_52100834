package com.example.lab3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnEdit;
    TextView txtViewName1;
    TextView txtViewJob;
    TextView txtViewName2;
    TextView txtViewPhone;
    TextView txtViewEmail;
    TextView txtViewAddress;
    TextView txtViewHomepage;
    ImageView avatarImageView;

    private static final int EDIT_PROFILE_REQUEST_CODE = 123; // Mã request code để gọi màn hình EditProfile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện
        btnEdit = findViewById(R.id.btnEdit);
        txtViewName1 = findViewById(R.id.txtViewName1);
        txtViewJob = findViewById(R.id.txtViewJob);
        txtViewName2 = findViewById(R.id.txtViewName2);
        txtViewPhone = findViewById(R.id.txtViewPhone);
        txtViewEmail = findViewById(R.id.txtViewEmail);
        txtViewAddress = findViewById(R.id.txtViewAddress);
        txtViewHomepage = findViewById(R.id.txtViewHomepage);
        avatarImageView = findViewById(R.id.imageView);

        // Xử lý sự kiện khi nhấn nút "Edit"
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để mở màn hình EditProfile
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Gửi dữ liệu hiện tại từ màn hình Profile qua Intent
                intent.putExtra("name", txtViewName1.getText().toString());
                intent.putExtra("email", txtViewEmail.getText().toString());
                intent.putExtra("job", txtViewJob.getText().toString());
                intent.putExtra("phone", txtViewPhone.getText().toString());
                intent.putExtra("address", txtViewAddress.getText().toString());
                intent.putExtra("homepage", txtViewHomepage.getText().toString());

                // Gọi màn hình EditProfile và chờ kết quả trả về
                startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE);
            }
        });
    }

    // Xử lý kết quả trả về từ màn hình EditProfile
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Nhận dữ liệu đã chỉnh sửa từ Intent
            String editedName = data.getStringExtra("editedName");
            String editedJob = data.getStringExtra("editedJob");
            String editedPhone = data.getStringExtra("editedPhone");
            String editedEmail = data.getStringExtra("editedEmail");
            String editedAddress = data.getStringExtra("editedAddress");
            String editedHomepage = data.getStringExtra("editedLink");

            // Cập nhật thông tin trên màn hình Profile
            txtViewName1.setText(editedName);
            txtViewName2.setText(editedName);
            txtViewJob.setText(editedJob);
            txtViewPhone.setText(editedPhone);
            txtViewEmail.setText(editedEmail);
            txtViewAddress.setText(editedAddress);
            txtViewHomepage.setText(editedHomepage);

            // Cập nhật hình ảnh đại diện nếu có
            Bitmap editedAvatar = data.getParcelableExtra("editedAvatar");
            if (editedAvatar != null) {
                avatarImageView.setImageBitmap(editedAvatar);
            }
        }
    }
}