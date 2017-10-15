package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.io.IOException;
import java.util.List;

public class Main {

    private static String BOT_TOKEN = "339428453:AAHM90686AbdTuE-77Qa8cNaxkJU0arR7Ws";

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot(BOT_TOKEN);

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

        final GetUpdatesResponse updatesResponse = bot.execute(getUpdates);

//        // sync
//        List<Update> updates = updatesResponse.updates();
//        for (Update update : updates) {
//            System.out.println(Message message = update.message());
//        }


// async
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
//            @Override
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = updatesResponse.updates();
                for (Update update : updates) {
                    System.out.println(update.message().toString());
                }
            }

//            @Override
            public void onFailure(GetUpdates request, IOException e) {

            }
        });

    }
}
