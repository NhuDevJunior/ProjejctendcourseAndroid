package com.example.projectendcourse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Viewholder> {
    List<Contact> contacts;
    IonClickContact ionClickContact;

    public AdapterContact(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.itemcontact, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Contact contact = contacts.get(position);
        Picasso.get().load(contact.getAvatar()).into(holder.imghinh);
        holder.name.setText(contact.getName());
        holder.name.setTextColor(Color.WHITE);
        holder.date.setTextColor(Color.WHITE);
        holder.date.setText(contact.getDate());


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name, date;
        ImageView imghinh;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            imghinh = itemView.findViewById(R.id.avatar);

        }
    }


}
