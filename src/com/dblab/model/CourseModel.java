package com.dblab.model;

public class CourseModel {
    public int code;
    public String courseName;
    public int category;
    public int credit;
    public String teacher;
    public int day1Start;
    public int day1End;
    public int day2Start;
    public int day2End;
    public int day3Start;
    public int day3End;
    public long examDate;
    public int examDurationMinute;
    public int semester;

    public boolean isDataReady() {
        // TODO: implement
        return false;
    }
}
