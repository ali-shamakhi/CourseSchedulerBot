package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/4/2017.
 */
public class StateRegistrationGetLastName {
    public static final String VALUE = "REGISTRATION_GET_Last_NAME";

    public static void validate(Message message) throws SQLException {
        DBHelper.setStudentField(message.from().id(), DBHelper.FIELD_LastName, message.text());
        // Will change to DBHelper.setStudentState(message.from().id(), StateRegistrationGetMajor.VALUE);
        // And DBHelper.setStudentSubstate(message.from().id(), StateRegistrationGetMajorName.VALUE);
        DBHelper.setStudentState(message.from().id(), StateRegistrationGetMajorName.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "What is your major?");
    }
}
