package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

public class StateFunctionList {
    public static final String VALUE = "FUNCTION_LIST";

    public static void validate(Message message) throws SQLException {
        if (message.text().equals("/addcourse")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/listcourses")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/deletecourse")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/editprofile")) {
            DBHelper.setStudentState(message.from().id(), StateNotRegistered.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "/edit");
        }
    }
}
