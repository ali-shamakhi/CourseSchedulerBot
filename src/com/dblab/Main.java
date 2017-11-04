package com.dblab;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static TelegramBot bot = null;

    public static void main(String[] args) {

        bot = new TelegramBot(LocalConfigs.BOT_TOKEN);

        Connection connection = null;

        DBHelper.init();

        bot.setUpdatesListener(new UpdatesListener() {
//            @Override
            public int process(List<Update> updates) {

                for (Update update : updates) {
                    try {
                        conn.createStatement().execute("INSERT INTO log(text, user_id) VALUES(\"" + update.message().text() + "\", " + update.message().from().id() + ")");
                        StateHandler.handleState(update.message(), conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
}
