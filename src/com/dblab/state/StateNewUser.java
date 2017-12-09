package com.dblab.state;

import com.dblab.Commands;
import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateNewUser {
    public static final String VALUE = "NEW_USER";

    public static void validate(Message message) throws SQLException {
        DBHelper.setStudentState(message.from().id(), StateNotRegistered.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "Welcome to course scheduler bot.\nPlease register first.\n" + Commands.REGISTER);
    }
}
