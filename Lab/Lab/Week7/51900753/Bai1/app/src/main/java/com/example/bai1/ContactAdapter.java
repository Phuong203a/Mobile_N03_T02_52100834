package com.example.bai1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> listContact;

    ContactAdapter(ArrayList<Contact> listProduct) {
        this.listContact = listProduct;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Contact getItem(int position) {
        return listContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewContact;

        if (convertView == null) {
            viewContact = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        } else viewContact = convertView;

        //Bind sữ liệu phần tử vào View
        Contact contact = getItem(position);
        ((TextView) viewContact.findViewById(R.id.tvName)).setText(contact.getName());
        ((TextView) viewContact.findViewById(R.id.tvNumber)).setText(contact.getNumber());

        return viewContact;
    }
}
