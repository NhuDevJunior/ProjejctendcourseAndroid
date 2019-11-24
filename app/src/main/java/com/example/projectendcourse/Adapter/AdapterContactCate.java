package com.example.projectendcourse.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectendcourse.contact.Contact;
import com.example.projectendcourse.interfaces.IonClickContact;
import com.example.projectendcourse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterContactCate extends RecyclerView.Adapter<AdapterContactCate.Viewholder> {
    List<Contact> contacts;
    IonClickContact ionClickContact;

    public AdapterContactCate(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.itemcate, parent, false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.name.setTextSize(15);
        holder.name.setTextColor(Color.WHITE);
        Picasso.get().load(contact.getAvatar()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imghinh;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.titlecate);
            imghinh = itemView.findViewById(R.id.avatarcate);

        }
    }


}

