package com.dblab.state;

import com.dblab.Communicator;
import com.dblab.DBHelper;
import com.dblab.Main;
import com.pengrad.telegrambot.model.Message;

import java.sql.SQLException;

/**
 * Created by sanei on 11/5/2017.
 */
public class StateRegistrationGetEntranceYear {
    public static final String VALUE = "REGISTRATION_GET_ENTRANCE_YEAR";

    public static void validate(Message message) throws SQLException {
        if (1301 < Integer.parseInt(message.text()) && Integer.parseInt(message.text()) < 1397) {
//            DBHelper.setMajorTempField(message.from().id(),DBHelper.FIELD_EntranceYear, Integer.parseInt(message.text()));
//            DBHelper.setStudentMajorID(message.from().id());
            DBHelper.setStudentState(message.from().id(), StateMainScreen.VALUE);
            Communicator.sendMessage(Main.bot, message.chat().id(), "The registration was successful!\nPress /menu to see all functions.");
        }
        else {
            Communicator.sendMessage(Main.bot, message.chat().id(), "Entrance year should be between 1301 to 1396");
        }
    }
}
