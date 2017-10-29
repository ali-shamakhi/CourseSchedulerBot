package com.dblab;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import java.sql.Connection;
import java.sql.SQLException;

public class StateNewUser{
    static void validate(Message message) {
        Communicator.sendMessage(Main.bot, message.chat().id(), "Welcome to course scheduler bot.\nPlease register first.\n/registration");
    }
//    static void setState(long chatID, String state, final Connection connection) throws SQLException {
//        connection.createStatement().execute("INSERT INTO user_state_tbl (chat_id, state) VALUES (" + chatID + ",\"" + state + "\")");
//    }
}
