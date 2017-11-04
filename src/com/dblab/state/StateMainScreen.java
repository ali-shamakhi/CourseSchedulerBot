package com.dblab.state;

import com.dblab.Main;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;

public class StateMainScreen {
    public static final String VALUE = "MAIN_SCREEN";

    public static void validate(Message message) {

        SendMessage request;

        if (message.text().equals("/menu")) {
            request = new SendMessage(message.chat().id(), "1. Add course\n2. List courses\n3. Delete course")
                    .parseMode(ParseMode.Markdown)
                    .disableWebPagePreview(true)
                    .disableNotification(true);
        } else {
            request = new SendMessage(message.chat().id(), "Press /menu to see available functions.")
                    .parseMode(ParseMode.Markdown)
                    .disableWebPagePreview(true)
                    .disableNotification(true);
        }

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
