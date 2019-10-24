package com.example.projectendcourse;

import java.io.Serializable;

public class Contact implements Serializable {
    String name;
    String date;
    String avatar;
    String linkvideo;
    public Contact(String name, String date, String avatar, String linkvideo) {
        this.name = name;
        this.date = date;
        this.avatar = avatar;
        this.linkvideo = linkvideo;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }
}
