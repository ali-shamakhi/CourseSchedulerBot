package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

public class StateNewUser{
    public static final String VALUE = "NEW_USER";

    public static void validate(Message message) {
        Communicator.sendMessage(Main.bot, message.chat().id(), "Welcome to course scheduler bot.\nPlease register first.\n/registration");
    }
//    static void setState(long chatID, String state, final Connection connection) throws SQLException {
//        connection.createStatement().execute("INSERT INTO user_state_tbl (chat_id, state) VALUES (" + chatID + ",\"" + state + "\")");
//    }
}
