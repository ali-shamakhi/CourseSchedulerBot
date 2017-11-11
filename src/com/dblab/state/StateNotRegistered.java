package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

public class StateNotRegistered {
    public static final String VALUE = "NOT_REGISTERED";

    public static void validate(Message message) throws SQLException {
        if (message.text().equals("/registration")) {
            DBHelper.setStudentState(message.from().id(), StateRegistrationGetFirstName.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "Enter your first name please:");
        } else {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Please register first.\n Click /registration");
        }
    }
}
