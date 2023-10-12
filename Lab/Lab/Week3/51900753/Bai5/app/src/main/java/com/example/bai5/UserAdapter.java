package com.example.bai5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        User user = getItem(i);


        if (convertView == null) {
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        }

        //Bind sữ liệu phần tử vào View
        ((TextView) convertView.findViewById(R.id.tvName)).setText(String.format(user.name));
        ((TextView) convertView.findViewById(R.id.tvEmail)).setText(String.format(user.email));

        return convertView;
    }
}
