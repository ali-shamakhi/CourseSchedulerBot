package com.dblab;

import com.dblab.state.*;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

class StateHandler {

    static void handleMessage(Message incomingMessage) throws SQLException {
        String state = DBHelper.getStudentState(incomingMessage.from().id());
        if (state == null) {
            DBHelper.createNewStudent(incomingMessage.from().id());
            DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetFirstName.VALUE);
            StateNewUser.validate(incomingMessage);
        } else {
            if (state.equals(StateMainScreen.VALUE)) {
                DBHelper.setStudentState(incomingMessage.from().id(), StateFunctionList.VALUE);
                StateMainScreen.validate(incomingMessage);
            }
            else if (state.equals(StateFunctionList.VALUE)) {
                StateFunctionList.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetFirstName.VALUE)) {
                StateRegistrationGetFirstName.validate(incomingMessage);
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetLastName.VALUE);
            }
            else if (state.equals(StateRegistrationGetLastName.VALUE)) {
                DBHelper.setStudentField(incomingMessage.from().id(),DBHelper.FIELD_FirstName, incomingMessage.text());
                StateRegistrationGetLastName.validate(incomingMessage);
//                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetLastName.VALUE);
            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.chat().id());
            }
        }
    }

}
