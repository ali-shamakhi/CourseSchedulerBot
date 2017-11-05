package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

public class StateFunctionList {
    public static final String VALUE = "FUNCTION_LIST";

    public static void validate(Message message) throws SQLException {
        if (message.text().equals("/show_profile")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/edit_profile")) {
            DBHelper.setStudentState(message.from().id(), StateRegistrationGetFirstName.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter your first name please:");
        }
        else if (message.text().equals("/add_course")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/list_courses")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
        }
        else if (message.text().equals("/delete_course")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Not Implemented Yet!");
            }
    }
}
