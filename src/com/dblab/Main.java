package com.dblab;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static TelegramBot bot = null;

    public static void main(String[] args) {

        DBHelper.init();

        bot = new TelegramBot(LocalConfigs.BOT_TOKEN);

        bot.setUpdatesListener(new UpdatesListener() {
//            @Override
            public int process(List<Update> updates) {

                for (Update update : updates) {
                    try {
                        StateHandler.handleMessage(update.message());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
}
