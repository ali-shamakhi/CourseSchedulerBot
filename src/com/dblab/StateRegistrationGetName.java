package com.dblab;

import com.pengrad.telegrambot.model.Message;

/**
 * Created by sanei on 10/29/2017.
 */
public class StateRegistrationGetName {
    static void validate(Message message) {
        if (message.text().equals("/registration")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "OK, Let's Register!\nEnter your name:");
        }

    }
}
