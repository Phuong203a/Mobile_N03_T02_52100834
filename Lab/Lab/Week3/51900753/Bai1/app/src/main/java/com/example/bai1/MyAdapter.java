package com.example.bai1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    List<String> listString;

    public MyAdapter(List<String> listString) {
        this.listString = listString;
    }

    @Override
    public int getCount() {
        return listString.size();
    }

    @Override
    public String getItem(int i) {
        return listString.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_string, viewGroup, false);
        }

        ((TextView) view.findViewById(R.id.textView)).setText(listString.get(i));

        return view;
    }
}
