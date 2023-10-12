package com.example.bai3;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item implements Serializable {
    int id;
    String name;
    String place;
    Calendar calendar;
    boolean checked;

    public Item(String name, String place, Calendar calendar) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.calendar = calendar;
        checked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String datetime = sdf.format(calendar.getTime());
        return datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
