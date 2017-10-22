package com.dblab;

import com.pengrad.telegrambot.model.Message;
import com.sun.glass.ui.EventLoop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sanei on 10/22/2017.
 */
public class StateHandler {

//    public static final String S_NEW_USER = "NEW_USER";
    public static final String S_MAIN_SCREEN = "MAIN_SCREEN";
    public static final String S_FUNCTION_LIST = "FUNCTION_LIST";

    static void handleState(Message incomingMessage, final Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery("SELET state from user_state_tbl WHERE chat_id = " + incomingMessage.chat().id());
        String state = null;
        while (result.next()) {
            state = result.getString("state");
        }
        if (state == null) {
            StateNewUser.validate(incomingMessage.from().id());
        } else {
            if (state.equals(S_MAIN_SCREEN)) {
                StateMainScreen.validate(incomingMessage.from().id());
            }
            else if (state.equals(S_FUNCTION_LIST)) {
                StateFunctionList.validate(incomingMessage.from().id());
            }
            else {
                System.err.println("Unknown state " + state + " for chat_id " + incomingMessage.from().id());
            }
        }
    }

    static void changeState(int chatID, String newState, final Connection connection) throws SQLException {
        connection.createStatement().execute("UPDATE user_state_tbl SET state = \"" + newState + "\" WHERE chat_id = " + chatID);
    }
}
