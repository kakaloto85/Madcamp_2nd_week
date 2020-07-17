package com.example.tab_application;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class PhoneBook {
    private String name, phone;
    public PhoneBook(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String string) {
        this.phone = string;
    }

    public void setName(String string) {
        this.name = string;
    }

}