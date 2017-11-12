package com.dblab.state;

import com.dblab.DBHelper;
import com.dblab.model.MajorModel;
import com.dblab.model.UserMap;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/12/2017.
 */
public class StateRegistrationGetMajor {
    public static final String VALUE = "REGISTRATION_GET_MAJOR";

    public static void validate(Message message, UserMap<MajorModel> userMajorMap) throws SQLException {
        String substate = DBHelper.getStudentSubstate(message.from().id());
        if (substate.equals(StateRegistrationGetMajorName.VALUE)) {
            StateRegistrationGetMajorName.validate(message, userMajorMap);
        }
        else if (substate.equals(StateRegistrationGetUniversity.VALUE)) {
            StateRegistrationGetUniversity.validate(message, userMajorMap);
        }
        else if (substate.equals(StateRegistrationGetEntranceYear.VALUE)) {
            StateRegistrationGetEntranceYear.validate(message, userMajorMap);
        }
    }
}