package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

public class StateMainScreen {
    public static final String VALUE = "MAIN_SCREEN";

    public static void validate(Message message) throws SQLException {
        if (message.text().equals("/menu")) {
            DBHelper.setStudentState(message.from().id(), StateFunctionList.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "1. /addcourse\n2. /listcourses\n3. /deletecourse\n4. /editprofile");
        }
        else {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Press /menu to see available functions.");
        }
    }
}
