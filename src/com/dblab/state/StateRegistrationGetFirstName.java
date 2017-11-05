package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 10/29/2017.
 */
public class StateRegistrationGetFirstName {
    public static final String VALUE = "REGISTRATION_GET_First_NAME";

    public static void validate(Message message) throws SQLException {
        DBHelper.setStudentField(message.from().id(),DBHelper. FIELD_FirstName, message.text());
        DBHelper.setStudentState(message.from().id(), StateRegistrationGetLastName.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "Hi " + message.text() + "!\nNow enter your last name please:");
    }
}
