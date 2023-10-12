package com.example.bai3;

public class Item {
    String name;
    boolean checked;

    public Item(String name) {
        this.name = name;
        this.checked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
