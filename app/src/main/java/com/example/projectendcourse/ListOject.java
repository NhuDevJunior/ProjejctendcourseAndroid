package com.example.projectendcourse;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOject implements Serializable {
    ArrayList<Contact> arrayList=new ArrayList<>();

    public ListOject(ArrayList<Contact> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<Contact> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Contact> arrayList) {
        this.arrayList = arrayList;
    }
}
