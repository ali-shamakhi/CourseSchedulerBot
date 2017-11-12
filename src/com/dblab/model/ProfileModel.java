package com.dblab.model;

public class ProfileModel {
    public String firstName;
    public String lastName;
    public String majorName;
    public String university;
    public int entranceYear;

    public boolean isDataReady() {
        return (firstName != null && !firstName.trim().equals("")
             && lastName != null && !lastName.trim().equals("")
             && majorName != null && !majorName.trim().equals("")
             && university != null && !university.trim().equals("")
             && entranceYear != 0
        );
    }
}
