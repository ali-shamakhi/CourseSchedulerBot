package com.dblab.state;

import com.dblab.Categories;
import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.dblab.model.CourseModel;
import com.dblab.model.UserMap;
import com.dblab.util.Convert;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/12/2017.
 */
public class StateCreatingNewCourse {
    public static final String VALUE = "CREATING_NEW_COURSE";
    // substates
    public static final String GET_CODE = "GET_CODE";
    public static final String GET_COURSE_NAME = "GET_COURSE_NAME";
    public static final String GET_CATEGORY = "GET_CATEGORY";
    public static final String GET_CREDIT = "GET_CREDIT";
    public static final String GET_TEACHER = "GET_TEACHER";
    public static final String GET_DAY1START = "GET_DAY1START";
    public static final String GET_DAY1END = "GET_DAY1END";
    public static final String GET_DAY2START = "GET_DAY2START";
    public static final String GET_DAY2END = "GET_DAY2END";
    public static final String GET_DAY3START = "GET_DAY3START";
    public static final String GET_DAY3END = "GET_DAY3END";
    public static final String GET_EXAM_DATE = "GET_EXAM_DATE";
    public static final String GET_EXAM_DURATION_MINUTE = "GET_EXAM_DURATION_MINUTE";
    public static final String GET_SEMESTER = "GET_SEMESTER";

    public static void validate(Message message, UserMap<CourseModel> userCourseMap) throws SQLException {
        String substate;
        if (!userCourseMap.containsKey(message.from().id())) {
            DBHelper.setStudentSubstate(message.from().id(), GET_CODE);
            substate = GET_CODE;
        } else {
            substate = DBHelper.getStudentSubstate(message.from().id());
            if (substate == null) {
                DBHelper.setStudentSubstate(message.from().id(), GET_CODE);
                substate = GET_CODE;
            }
        }
        if (substate.equals(GET_CODE)) {
            CourseModel courseModel;
            if (userCourseMap.containsKey(message.from().id())) courseModel = userCourseMap.get(message.from().id());
            else courseModel = new CourseModel();
            try {
                courseModel.code = Integer.parseInt(message.text());
                if (courseModel.code >= 100000000 && courseModel.code <= 999999999) {
                    DBHelper.setStudentSubstate(message.from().id(), GET_COURSE_NAME);
                    Communicator.sendMessage(Main.bot, message.chat().id(), "Enter course name:");
                } else {
                    Communicator.sendMessage(Main.bot, message.chat().id(), "Course code must be 9 digits in form of FFMMCCCGG\nEnter course code again:");
                }
            } catch (NumberFormatException e) {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Course code must be 9 digits in form of FFMMCCCGG\nEnter course code again:");
            }
        }
        else if (substate.equals(GET_COURSE_NAME)) {
            String courseName = message.text();
            if (!courseName.trim().equals("")) {
                userCourseMap.get(message.from().id()).courseName = courseName;
                DBHelper.setStudentSubstate(message.from().id(), GET_CATEGORY);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Choose course category:");
                // TODO: buttons
            } else {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Please enter course name:");
            }
        }
        else if (substate.equals(GET_CATEGORY)) {
            // TODO: check whether it operates with buttons
            String categoryName = message.text();
            if (!categoryName.trim().equals("")) {
                userCourseMap.get(message.from().id()).category = Categories.getCode(categoryName);
                DBHelper.setStudentSubstate(message.from().id(), GET_CREDIT);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter course credit:\nA course credit should be between 0 to 6");
            } else {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Please choose one of the course categories below:");
                // TODO: buttons
            }
        }
        else if (substate.equals(GET_CREDIT)) {
            int credit;
            try {
                credit = Integer.parseInt(message.text());
                if (0 <= credit && credit <= 6) {
                    userCourseMap.get(message.from().id()).credit = credit;
                    DBHelper.setStudentSubstate(message.from().id(), GET_TEACHER);
                    Communicator.sendMessage(Main.bot, message.chat().id(), "Enter teacher's name:");
                }
                else {
                    Communicator.sendMessage(Main.bot, message.chat().id(), "A course credit should be between 0 to 6.\nPlease enter course credit again:");
                }
            }
            catch (NumberFormatException e) {
                Communicator.sendMessage(Main.bot, message.chat().id(), "A course credit should be an integer number between 0 to 6.\nPlease enter course credit again:");
            }
        }
        else if (substate.equals(GET_TEACHER)) {
            String teacherName = message.text();
            if (!teacherName.trim().equals("")) {
                userCourseMap.get(message.from().id()).teacher = teacherName;
                DBHelper.setStudentSubstate(message.from().id(), GET_DAY1START);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the start of the first session of course in week by this format:\n" +
                        "Weekday HH:mm\n" +
                        "(HH in 24 hour format)\n");
            } else {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Please enter teacher's name");
            }
        }
        else if (substate.equals(GET_DAY1START)) {
            int day1Start = Convert.getWeekMinute(message.text());
            userCourseMap.get(message.from().id()).day1Start = day1Start;
            DBHelper.setStudentSubstate(message.from().id(), GET_DAY1END);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the end of the first session of course in week by this format:\n" +
                    "Weekday HH:mm\n" +
                    "(HH in 24 hour format)\n");
        }
        else if (substate.equals(GET_DAY1END)) {
            int day1End = Convert.getWeekMinute(message.text());
            userCourseMap.get(message.from().id()).day1End = day1End;
            DBHelper.setStudentSubstate(message.from().id(), GET_DAY2START);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the start of the second session of course in week by this format:\n" +
                    "Weekday HH:mm\n" +
                    "(HH in 24 hour format)\n"+
                    "Press /last if the course holds only one session in the week");
        }
        else if (substate.equals(GET_DAY2START)) {
            if (message.text().equals("/last")) {
                DBHelper.setStudentSubstate(message.from().id(), GET_EXAM_DATE);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter Exam Date in the format YY.MM.DD\nor press /no_exam");
            }
            else {
                int day2Start = Convert.getWeekMinute(message.text());
                userCourseMap.get(message.from().id()).day2Start = day2Start;
                DBHelper.setStudentSubstate(message.from().id(), GET_DAY2END);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the end of the second session of course in week by this format:\n" +
                        "Weekday HH:mm\n" +
                        "(HH in 24 hour format)\n");
            }
        }
        else if (substate.equals(GET_DAY2END)) {
            int day2End = Convert.getWeekMinute(message.text());
            userCourseMap.get(message.from().id()).day2End = day2End;
            DBHelper.setStudentSubstate(message.from().id(), GET_DAY3START);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the start of the third session of course in week by this format:\n" +
                    "Weekday HH:mm\n" +
                    "(HH in 24 hour format)\n"+
                    "Press /last if the course holds two sessions in the week");
        }
        else if (substate.equals(GET_DAY3START)) {
            if (message.text().equals("/last")) {
                DBHelper.setStudentSubstate(message.from().id(), GET_EXAM_DATE);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter Exam Date in the format YY.MM.DD\nor press /no_exam");
            }
            else {
                int day3Start = Convert.getWeekMinute(message.text());
                userCourseMap.get(message.from().id()).day3Start = day3Start;
                DBHelper.setStudentSubstate(message.from().id(), GET_DAY3END);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the end of the third session of course in week by this format:\n" +
                        "Weekday HH:mm\n" +
                        "(HH in 24 hour format)\n");
            }
        }
        else if (substate.equals(GET_DAY3END)) {
            int day3End = Convert.getWeekMinute(message.text());
            userCourseMap.get(message.from().id()).day3End = day3End;
            DBHelper.setStudentSubstate(message.from().id(), GET_EXAM_DATE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter Exam Date in the format YY.MM.DD\nor press /no_exam");
        }
        else if (substate.equals(GET_EXAM_DATE)) {
            if (message.text().equals("/no_exam")) {
                DBHelper.setStudentSubstate(message.from().id(), GET_SEMESTER);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter semester in the format YYYS:");
            }
            else {
                // TODO implement
                //int examDate = message.text();
                //userCourseMap.get(message.from().id()).examDate = examDate;
                DBHelper.setStudentSubstate(message.from().id(), GET_EXAM_DURATION_MINUTE);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter the duration of exam in minutes:");
            }
        }
        else if (substate.equals(GET_EXAM_DURATION_MINUTE)) {
            try {
                int examDuration = Integer.parseInt(message.text());
                userCourseMap.get(message.from().id()).examDurationMinute = examDuration;
                DBHelper.setStudentSubstate(message.from().id(), GET_SEMESTER);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter semester in the format YYYS:");
            }
            catch (NumberFormatException e) {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Duration must be an integer number\nEnter the duration of exam in minutes:");
            }
        }
        else if (substate.equals(GET_SEMESTER)) {}
    }
}