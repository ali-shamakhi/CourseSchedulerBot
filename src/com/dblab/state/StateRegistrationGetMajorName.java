package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationGetMajorName {
    public static final String VALUE = "REGISTRATION_GET_MAJOR_NAME";

    public static void validate(Message message) {
        Communicator.sendMessage(Main.bot, message.chat().id(), "What is your major?");
    }
}
