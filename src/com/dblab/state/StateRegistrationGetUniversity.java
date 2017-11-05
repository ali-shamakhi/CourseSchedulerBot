package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationGetUniversity {
    public static final String VALUE = "REGISTRATION_GET_UNIVERSITY";

    public static void validate(Message message) throws SQLException {
//        DBHelper.setMajorField(message.from().id(),DBHelper.FIELD_University, message.text());
        DBHelper.setStudentState(message.from().id(), StateRegistrationGetEntranceYear.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "What is your entrance year?\nEntrance year should be between 1385 to 1396");
    }
}
