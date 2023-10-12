package com.example.bai2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<String> pathList;
    List<Bitmap> imageBitmapList;

    public ImageAdapter(List<String> pathList) {
        this.pathList = pathList;
//        imageBitmapList = initBitmapImageList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.ivImageView.setImageBitmap(imageBitmapList.get(position));

        Glide.with(holder.ivImageView.getContext()).load(pathList.get(position)).into(holder.ivImageView);
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageView = itemView.findViewById(R.id.ivImageView);
        }
    }

    public List<Bitmap> initBitmapImageList() {
        List<Bitmap> imageBitmapList = new ArrayList<>();

        for (String imgPath : pathList) {
            File imgFile = new File(imgPath);

            if(imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageBitmapList.add(myBitmap);
            }
        }
        return imageBitmapList;
    }

}
