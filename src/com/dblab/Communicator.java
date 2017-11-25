package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.IllegalFormatException;

public class Communicator {

    public static void showProfile(TelegramBot bot, long chatID, int userID) throws SQLException {
        String firstName, lastName, majorName, university;
        int entranceYear;
        String profile;

        firstName = (String) DBHelper.getStudentField(userID, DBHelper.FIELD_FirstName);
        lastName = (String) DBHelper.getStudentField(userID, DBHelper.FIELD_LastName);
        majorName = (String) DBHelper.getMajorFieldForUser(userID, DBHelper.FIELD_MajorName);
        university = (String) DBHelper.getMajorFieldForUser(userID, DBHelper.FIELD_University);
        if (DBHelper.getMajorFieldForUser(userID, DBHelper.FIELD_EntranceYear) != null) {
            entranceYear = (Integer) DBHelper.getMajorFieldForUser(userID, DBHelper.FIELD_EntranceYear);
            profile = "Your Profile:\nName: " + firstName + " " + lastName + "\nMajor: " + majorName + "\nUniversity: " + university + "\nEntrance Year: " + entranceYear;
        }
        else {
            profile = "Your Profile:\nName: " + firstName + " " + lastName + "\nMajor: " + majorName + "\nUniversity: " + university + "\nEntrance Year: null";
        }

        sendMessage(bot, chatID, profile);
    }

    public static void sendMessage(TelegramBot bot, long chatID, String text) {
        SendMessage sm = new SendMessage(chatID, text)
//                .parseMode(ParseMode.Markdown)
                ;
        bot.execute(sm, new Callback<SendMessage, SendResponse>() {
            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {
                System.out.println(sendMessage.toString());
                System.out.println(sendResponse.toString());
            }

            public void onFailure(SendMessage sendMessage, IOException e) {
                System.err.println(sendMessage.toString());
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * Replaces every "%s" in <code>string</code> with corresponding <code>params</code>
     * @param string initial string
     * @param params values defined dynamically in runtime
     * @return the result string
     * @exception IllegalFormatException thrown if the length if <code>params</code> does not match the number of "%s" in <code>string</code>
     */
    public static String paramString(String string, String ... params) throws IllegalFormatException {
        if ((params == null || params.length == 0) && !string.contains("%s")) return string;
        return String.format(string, params);
    }

}
