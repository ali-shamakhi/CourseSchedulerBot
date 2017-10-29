package com.dblab;

import com.pengrad.telegrambot.model.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateHandler {

    public static final String S_MAIN_SCREEN = "MAIN_SCREEN";
    public static final String S_FUNCTION_LIST = "FUNCTION_LIST";

    static void handleState(Message incomingMessage, final Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery("SELECT state FROM user_state_tbl WHERE chat_id = " + incomingMessage.chat().id());
        String state = null;
        while (result.next()) {
            state = result.getString("state");
            break;
        }
        if (state == null) {
            StateNewUser.validate(incomingMessage);
            StateNewUser.setState(incomingMessage.chat().id(), S_MAIN_SCREEN, connection);
        } else {
            if (state.equals(S_MAIN_SCREEN)) {
                changeState(incomingMessage.chat().id(), S_FUNCTION_LIST, connection);
                StateMainScreen.validate(incomingMessage);
            }
            else if (state.equals(S_FUNCTION_LIST)) {
                StateFunctionList.validate(incomingMessage);
            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.chat().id());
            }
        }
    }

    static void changeState(long chatID, String newState, final Connection connection) throws SQLException {
        connection.createStatement().execute("UPDATE user_state_tbl SET state = \"" + newState + "\" WHERE chat_id = " + chatID);
    }
}
