package com.dblab.model;

public class CourseModel {
    public int code;
    public String courseName;
    public int category;
    public int credit;
    public String teacher;
    public Integer day1Start = -1;
    public Integer day1End = -1;
    public Integer day2Start = -1;
    public Integer day2End = -1;
    public Integer day3Start = -1;
    public Integer day3End = -1;
    public long examDate = -1;
    public int examDurationMinute = -1;
    public int semester;

    public boolean isDataReady() {
        // TODO: implement
        return false;
    }
}
