package com.example.projectendcourse;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListView extends BaseAdapter {
    public AdapterListView(List<Contact> contactList) {
        this.contactList = contactList;
    }

    List<Contact> contactList;

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        convertView=inflater.inflate(R.layout.itemautolist,parent,false);
        TextView nameauto=convertView.findViewById(R.id.autoname);
        ImageView imgauto=convertView.findViewById(R.id.autoimage);
        Contact contact=contactList.get(position);
        Picasso.get().load(contact.getAvatar()).into(imgauto);
        nameauto.setText(contact.getName());
        nameauto.setTextColor(Color.WHITE);
        return convertView;
    }
}
