package com.dblab;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static String BOT_TOKEN = "339428453:AAHM90686AbdTuE-77Qa8cNaxkJU0arR7Ws";

    static TelegramBot bot = null;

    public static void main(String[] args) {

        bot = new TelegramBot(BOT_TOKEN);

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/dblab_log", LocalConfigs.USERNAME, LocalConfigs.PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final Connection conn = connection;

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
