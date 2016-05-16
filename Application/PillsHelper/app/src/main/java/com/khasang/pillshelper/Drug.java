package com.khasang.pillshelper;

/**
 * Created by aleksandrlihovidov on 14.05.16.
 */
public class Drug {
    private int id;
    private String name;

    public Drug(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%5d: %s", id, name);
    }
}
