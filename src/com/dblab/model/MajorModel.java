package com.dblab.model;

public class MajorModel {
    public String name;
    public String university;
    public int entranceYear;

    public boolean isDataReady() {
        return (name != null && university != null && !name.trim().equals("") && !university.trim().equals("") && entranceYear > 0);
    }
}
