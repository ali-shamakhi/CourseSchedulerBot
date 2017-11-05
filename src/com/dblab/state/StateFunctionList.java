package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

public class StateFunctionList {
    public static final String VALUE = "FUNCTION_LIST";

    public static void validate(Message message) throws SQLException {
        if (message.text().equals("/Add_course")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/List_courses")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/Delete_course")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/Edit_profile")) {
            DBHelper.setStudentState(message.from().id(), StateNotRegistered.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "/edit");
        }
    }
}
