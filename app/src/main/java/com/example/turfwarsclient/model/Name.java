package com.example.turfwarsclient.model;

public class Name {
    private String type  = "Name";
    private String name;


    public Name(String name){
        this.name = name;
    }

    public Name() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
