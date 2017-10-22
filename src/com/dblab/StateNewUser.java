package com.dblab;


import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sanei on 10/22/2017.
 */
public class StateNewUser{
    static void validate(long chatID) {
        SendMessage request = new SendMessage(chatID, "Welcome!\nPress /menu to see available functions.")
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true)
                .disableNotification(true);

        Main.bot.execute(request, new Callback<SendMessage, SendResponse>() {
            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {
                System.out.println(sendMessage.toString());
                System.out.println(sendResponse.toString());
            }

            public void onFailure(SendMessage sendMessage, IOException e) {

            }
        });
    }
}
