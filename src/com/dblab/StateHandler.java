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
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetLastName.VALUE);
                StateRegistrationGetFirstName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetLastName.VALUE)) {
                DBHelper.setStudentField(incomingMessage.from().id(),DBHelper.FIELD_FirstName, incomingMessage.text());
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetMajorName.VALUE);
                StateRegistrationGetLastName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetMajorName.VALUE)) {
                DBHelper.setStudentField(incomingMessage.from().id(),DBHelper.FIELD_LastName, incomingMessage.text());
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetUniversity.VALUE);
                StateRegistrationGetMajorName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetUniversity.VALUE)) {
//                DBHelper.setMajorField(incomingMessage.from().id(),DBHelper.FIELD_MajorName, incomingMessage.text());
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetEntranceYear.VALUE);
                StateRegistrationGetUniversity.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetEntranceYear.VALUE)) {
//                DBHelper.setMajorField(incomingMessage.from().id(),DBHelper.FIELD_University, incomingMessage.text());
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationCompleted.VALUE);
                StateRegistrationGetEntranceYear.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationCompleted.VALUE)) {
//                DBHelper.setMajorField(incomingMessage.from().id(),DBHelper.FIELD_EntranceYear, incomingMessage.text());
                DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationCompleted.VALUE);
                StateRegistrationCompleted.validate(incomingMessage);
            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.chat().id());
            }
        }
    }

}
