package com.example.lab6_4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    Context context;
    File[] filesAndFolders;
    public MyAdapter(Context context, File[] filesAndFolders){
        this.context = context;
        this.filesAndFolders = filesAndFolders;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        File selectedFile = filesAndFolders[position];
        holder.file_name_TextView.setText(selectedFile.getName());

        if (selectedFile.isDirectory()){
            holder.iconView.setImageResource(R.drawable.baseline_create_new_folder_24);
        }else {
            holder.iconView.setImageResource(R.drawable.baseline_note_add_24);

        }
    }

    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView file_name_TextView;
        ImageView iconView;
        public ViewHolder(View itemView) {
            super(itemView);
            file_name_TextView = itemView.findViewById(R.id.file_name_textView);
            iconView = itemView.findViewById(R.id.iconView);
        }
    }
}
