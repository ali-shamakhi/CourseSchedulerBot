package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.dblab.model.CourseModel;
import com.dblab.model.UserMap;
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
            if (!message.text().trim().equals("")) {
                userCourseMap.get(message.from().id()).courseName = message.text();
                DBHelper.setStudentSubstate(message.from().id(), GET_CATEGORY);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter course category:");
            } else {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Please enter course category:");
            }
        }
        else if (substate.equals(GET_CATEGORY)) {
            String categoryName = message.text();
            // TODO: continue
            if (!message.text().trim().equals("")) {
                userCourseMap.get(message.from().id()).courseName = message.text();
                DBHelper.setStudentSubstate(message.from().id(), GET_CATEGORY);
                Communicator.sendMessage(Main.bot, message.chat().id(), "Enter course category:");
            } else {
                Communicator.sendMessage(Main.bot, message.chat().id(), "Please enter course category:");
            }
        }
        else if (substate.equals(GET_CREDIT)) {}
        else if (substate.equals(GET_TEACHER)) {}
        else if (substate.equals(GET_DAY1START)) {}
        else if (substate.equals(GET_DAY1END)) {}
        else if (substate.equals(GET_DAY2START)) {}
        else if (substate.equals(GET_DAY2END)) {}
        else if (substate.equals(GET_DAY3START)) {}
        else if (substate.equals(GET_DAY3END)) {}
        else if (substate.equals(GET_EXAM_DATE)) {}
        else if (substate.equals(GET_EXAM_DURATION_MINUTE)) {}
        else if (substate.equals(GET_SEMESTER)) {}
    }
}