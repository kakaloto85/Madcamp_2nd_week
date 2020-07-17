package com.example.tab_application;
import android.app.Application;

import java.util.ArrayList;

public class prefer_arr extends android.app.Application {
    private ArrayList<String> prefer_array = new ArrayList<String>();
    public ArrayList<String> get_prefer_arr(){
        return prefer_array;
    }
    public void add_prefer_arr(String imgpath){
        this.prefer_array.add(imgpath);
    }
    public boolean check_contains(String string){
        return this.prefer_array.contains(string);
    }
}
