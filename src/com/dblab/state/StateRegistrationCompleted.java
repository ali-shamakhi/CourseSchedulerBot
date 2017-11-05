package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationCompleted {
    public static final String VALUE = "REGISTRATION_COMPLETED";

    public static void validate(Message message) {
        Communicator.sendMessage(Main.bot, message.chat().id(), "The registration was successful!");
    }
}
