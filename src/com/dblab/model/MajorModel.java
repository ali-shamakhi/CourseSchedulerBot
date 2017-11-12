package com.dblab.model;

public class MajorModel {
    public String majorName;
    public String university;
    public int entranceYear;

    public boolean isDataReady() {
        return (majorName != null && university != null && !majorName.trim().equals("") && !university.trim().equals("") && entranceYear > 0);
    }
}
