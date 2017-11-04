package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

/**
 * Created by sanei on 10/29/2017.
 */
public class StateRegistrationGetName {
    public static final String VALUE = "REGISTRATION_GET_NAME";

    public static void validate(Message message) {
        if (message.text().equals("/registration")) {
            Communicator.sendMessage(Main.bot, message.chat().id(), "OK, Let's Register!\nEnter your name:");
        }

    }
}
