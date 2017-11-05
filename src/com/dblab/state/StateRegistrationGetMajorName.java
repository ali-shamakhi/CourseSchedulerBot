package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationGetMajorName {
    public static final String VALUE = "REGISTRATION_GET_MAJOR_NAME";

    public static void validate(Message message) throws SQLException {
//        DBHelper.setMajorField(message.from().id(),DBHelper.FIELD_MajorName, message.text());
        DBHelper.setStudentState(message.from().id(), StateRegistrationGetUniversity.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "What is the name of your university?");
    }
}
