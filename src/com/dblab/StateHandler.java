package com.dblab;

import com.dblab.state.*;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

class StateHandler {

    static void handleMessage(Message incomingMessage) throws SQLException {
        String state = DBHelper.getStudentState(incomingMessage.from().id());
        if (state == null) {
            DBHelper.createNewStudent(incomingMessage.from().id());
            StateNewUser.validate(incomingMessage);
        } else {
            if (state.equals(StateMainScreen.VALUE)) {
                StateMainScreen.validate(incomingMessage);
            }
            else if (state.equals(StateFunctionList.VALUE)) {
                StateFunctionList.validate(incomingMessage);
            }
            else if (state.equals(StateNotRegistered.VALUE)) {
                StateNotRegistered.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetFirstName.VALUE)) {
                StateRegistrationGetFirstName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetLastName.VALUE)) {
                StateRegistrationGetLastName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetMajorName.VALUE)) {
                StateRegistrationGetMajorName.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetUniversity.VALUE)) {
                StateRegistrationGetUniversity.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetEntranceYear.VALUE)) {
                StateRegistrationGetEntranceYear.validate(incomingMessage);
            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.chat().id());
            }
        }
    }

}
