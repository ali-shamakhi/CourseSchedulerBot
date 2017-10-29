package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;

public class Communicator {

    public static void sendMessage(TelegramBot bot, long chatID, String text) {
        SendMessage sm = new SendMessage(chatID, text)
                .parseMode(ParseMode.Markdown)
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

}
