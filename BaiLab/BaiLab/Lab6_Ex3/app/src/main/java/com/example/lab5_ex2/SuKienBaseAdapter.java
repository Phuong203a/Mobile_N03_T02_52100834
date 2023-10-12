package com.example.lab5_ex2;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SuKienBaseAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<SuKien> data;
    public SuKienBaseAdapter(Context context, int layout, ArrayList<SuKien> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.name = view.findViewById(R.id.name);
            holder.room = view.findViewById(R.id.room);
            holder.time = view.findViewById(R.id.time);
            holder.aSwitch = view.findViewById(R.id.app_bar_switch1);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(i).getTen());
        holder.room.setText(data.get(i).getPhong());
        holder.time.setText(data.get(i).getNgay() + "  " + data.get(i).getGio());
        holder.aSwitch.setChecked(Boolean.parseBoolean(data.get(i).getTrangthai()));

        return view;
    }

    private class ViewHolder {
        private TextView name;
        private TextView room;
        private TextView time;
        private Switch aSwitch;
    }

}
