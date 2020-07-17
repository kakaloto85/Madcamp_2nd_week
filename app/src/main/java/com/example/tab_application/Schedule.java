package com.example.tab_application;

public class Schedule {
    private String date;
    private String time;
    private String title;


    public Schedule (String title, String time, String date){
        this.date = date;
        this.title = title;
        this.time = time;

    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() { return title; }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String string) {
        this.time = string;
    }

    public void setTitle(String string) {
        this.title = string;
    }

}
