package com.example.healthtracker;

public class Medication {
    String title;
    String desc;
    String image;
    Long time;
    Long due;

    public Medication(String t, String d, String i, Long du){
        title = t;
        desc = d;
        image = i;
        due = du;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc(){
        return desc;
    }

    public String getImage(){
        return image;
    }

    public Long getDue(){
        return due;
    }
}
