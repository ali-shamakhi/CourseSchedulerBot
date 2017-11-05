package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

/**
 * Created by sanei on 11/4/2017.
 */
public class StateRegistrationGetLastName {
    public static final String VALUE = "REGISTRATION_GET_Last_NAME";

    public static void validate(Message message) {
        Communicator.sendMessage(Main.bot, message.chat().id(), "Hi " + message + "!\nNow enter your last name please:");
    }
}
