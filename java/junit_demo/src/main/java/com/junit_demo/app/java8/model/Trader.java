package com.junit_demo.app.java8.model;

public class Trader {
    private String name;
    private String city;

    public Trader() {
    }

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}