package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;import android.content.Intent;import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    Button btnCamera;
    ImageView avatarImageView; // ImageView để hiển thị hình ảnh đại diện
    EditText edtTextJob;
    EditText edtTextName;
    EditText edtTextPhone;
    EditText edtTextEmail;
    EditText edtTextAddress;
    EditText edtTextLink;
    Button btnSave;

    private Bitmap avatarBitmap; // Biến để lưu hình ảnh đại diện mới

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Khởi tạo các thành phần giao diện
        btnCamera = findViewById(R.id.btnCamera);
        avatarImageView = findViewById(R.id.imageView2);
        edtTextJob = findViewById(R.id.edtTextJob);
        edtTextName = findViewById(R.id.edtTextName);
        edtTextPhone = findViewById(R.id.edtTextPhone);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextAddress = findViewById(R.id.edtTextAddress);
        edtTextLink = findViewById(R.id.edtTextLink);
        btnSave = findViewById(R.id.btnSave);

        // Nhận dữ liệu từ Intent được gửi từ MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String job = intent.getStringExtra("job");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        String homepage = intent.getStringExtra("homepage");

        // Hiển thị dữ liệu trên các EditText tương ứng
        edtTextJob.setText(job);
        edtTextName.setText(name);
        edtTextPhone.setText(phone);
        edtTextEmail.setText(email);
        edtTextAddress.setText(address);
        edtTextLink.setText(homepage);

        // Xử lý sự kiện khi nhấn nút "Chụp hình"
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sử dụng Intent để mở ứng dụng camera
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 123); // 123 là mã request code
            }
        });

        // Xử lý sự kiện khi nhấn nút "Save"
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText sau khi chỉnh sửa
                String editedName = edtTextName.getText().toString();
                String editedJob = edtTextJob.getText().toString();
                String editedPhone = edtTextPhone.getText().toString();
                String editedEmail = edtTextEmail.getText().toString();
                String editedAddress = edtTextAddress.getText().toString();
                String editedLink = edtTextLink.getText().toString();

                // Trả về dữ liệu đã chỉnh sửa qua Intent để cập nhật trên màn hình Profile
                Intent resultIntent = new Intent();
                resultIntent.putExtra("editedName", editedName);
                resultIntent.putExtra("editedJob", editedJob);
                resultIntent.putExtra("editedPhone", editedPhone);
                resultIntent.putExtra("editedEmail", editedEmail);
                resultIntent.putExtra("editedAddress", editedAddress);
                resultIntent.putExtra("editedLink", editedLink);

                // Nếu có hình ảnh mới, gửi hình ảnh đại diện cùng với dữ liệu
                if (avatarBitmap != null) {
                    resultIntent.putExtra("editedAvatar", avatarBitmap);
                }

                setResult(RESULT_OK, resultIntent);
                finish(); // Kết thúc màn hình EditProfile và quay lại màn hình Profile
            }
        });
    }

    // Xử lý kết quả sau khi chụp hình
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            // Lấy hình ảnh từ Intent
            Bundle extras = data.getExtras();
            avatarBitmap = (Bitmap) extras.get("data");

            // Hiển thị hình ảnh lên ImageView
            avatarImageView.setImageBitmap(avatarBitmap);
        }
    }
}

