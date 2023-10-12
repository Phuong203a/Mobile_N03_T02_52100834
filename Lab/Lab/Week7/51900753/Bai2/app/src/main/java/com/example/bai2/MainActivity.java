package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    GridView gridView;
    RecyclerView recyclerView;
    List<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        gridView = findViewById(R.id.gvGridView);
        recyclerView = findViewById(R.id.rvRV);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            gridView.setAdapter(new ImageAdapter(this));
            imageList = getAllShownImagesPath();
            recyclerView.setAdapter(new ImageAdapter(imageList));
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    public ArrayList<String> getAllShownImagesPath() {
        ArrayList<String> galleryImagePaths = new ArrayList<String>();
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID}; //get all columns of type images
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;//order data by date

        Cursor imagecursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,null,null,orderBy + " DESC"
//                columns,null,null,null
        ); //get all data in Cursor by sorting in DESC order

        int i = 0;
        while (i <= 15) {
            imagecursor.moveToNext();
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA); //get column index
            galleryImagePaths.add(imagecursor.getString(dataColumnIndex)); //get Image from column index
            i++;
        }
        return galleryImagePaths;
    }
}