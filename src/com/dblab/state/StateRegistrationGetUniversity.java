package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.dblab.model.MajorModel;
import com.dblab.model.UserMap;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationGetUniversity {
    public static final String VALUE = "REGISTRATION_GET_UNIVERSITY";

    public static void validate(Message message, UserMap<MajorModel> userMajorMap) throws SQLException {
        userMajorMap.get(message.from().id()).university = message.text();
        DBHelper.setStudentSubstate(message.from().id(), StateRegistrationGetEntranceYear.VALUE);
        //- DBHelper.setStudentState(message.from().id(), StateRegistrationGetEntranceYear.VALUE);
        Communicator.sendMessage(Main.bot, message.chat().id(), "What is your entrance year?\nEntrance year should be between 1301 to 1396");
    }
}
