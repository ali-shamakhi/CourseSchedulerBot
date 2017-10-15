package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;

public class Main {

    private static String BOT_TOKEN = "339428453:AAHM90686AbdTuE-77Qa8cNaxkJU0arR7Ws";

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot(BOT_TOKEN);

//        bot.execute(request, new Callback() {
//            @Override
//            public void onResponse(BaseRequest request, BaseResponse response) {
//
//            }
//            @Override
//            public void onFailure(BaseRequest request, IOException e) {
//
//            }
//        });
    }
}
