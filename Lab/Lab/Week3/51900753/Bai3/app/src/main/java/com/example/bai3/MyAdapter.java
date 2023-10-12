package com.example.bai3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    List<Integer> toBeDeleted = new ArrayList<>();
    List<Item> listString;

    public MyAdapter(List<Item> listString) {
        this.listString = listString;
    }

    @Override
    public int getCount() {
        return listString.size();
    }

    @Override
    public Item getItem(int i) {
        return listString.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Item str = getItem(position);
        ViewHolder v;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_string, viewGroup, false);

            v = new ViewHolder();
            v.textView = view.findViewById(R.id.textView);
            v.checkBox = view.findViewById(R.id.checkBox);

            v.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if (b) {
//                        toBeDeleted.add(position);
//                    } else {
//                        for (int i = 0; i < toBeDeleted.size(); i++) {
//                            if (toBeDeleted.get(i) == position)
//                                toBeDeleted.remove(i);
//                        }
//                    }

                    listString.get(position).checked = b;
                }
            });
            view.setTag(v);
        } else {
            v = (ViewHolder) view.getTag();
        }

        v.textView.setText(str.getName());

        return view;
    }
}
