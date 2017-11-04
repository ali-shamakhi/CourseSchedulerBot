package com.dblab;

import com.dblab.state.StateFunctionList;
import com.dblab.state.StateMainScreen;
import com.dblab.state.StateNewUser;
import com.dblab.state.StateRegistrationGetName;
import com.pengrad.telegrambot.model.Message;

import java.sql.Connection;
import java.sql.SQLException;

class StateHandler {

    static void handleMessage(Message incomingMessage) throws SQLException {
//        ResultSet result = connection.createStatement().executeQuery("SELECT state FROM user_state_tbl WHERE chat_id = " + incomingMessage.chat().id());

        String state = DBHelper.getStudentState(incomingMessage.from().id());
        if (state == null) {
            StateNewUser.validate(incomingMessage);
//            StateNewUser.setState(incomingMessage.chat().id(), StateMainScreen.VALUE, connection);
            DBHelper.setStudentState(incomingMessage.from().id(), StateRegistrationGetName.VALUE);
        } else {
            if (state.equals(StateMainScreen.VALUE)) {
                DBHelper.setStudentState(incomingMessage.from().id(), StateFunctionList.VALUE);
                StateMainScreen.validate(incomingMessage);
            }
            else if (state.equals(StateFunctionList.VALUE)) {
                StateFunctionList.validate(incomingMessage);
            }
            else if (state.equals(StateRegistrationGetName.VALUE)) {

            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.chat().id());
            }
        }
    }

//    static void changeState(long chatID, String newState, final Connection connection) throws SQLException {
//        connection.createStatement().execute("UPDATE user_state_tbl SET state = \"" + newState + "\" WHERE chat_id = " + chatID);
//    }
}
