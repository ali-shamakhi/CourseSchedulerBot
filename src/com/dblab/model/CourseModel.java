package com.dblab.model;

public class CourseModel {
    public int code;
    public String courseName;
    public int category;
    public int credit;
    public String teacher;
    public int day1Start;
    public int day1End;
    public int day2Start = -1;
    public int day2End = -1;
    public int day3Start = -1;
    public int day3End = -1;
    public long examDate = -1;
    public int examDurationMinute = -1;
    public int semester;

    public boolean isDataReady() {
        // TODO: implement
        return false;
    }
}
