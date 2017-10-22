package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.io.IOException;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/dblab_log", "bot_user", "botbotpass");
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

//                    System.out.println(update.message().from());

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

//        // sync
//        List<Update> updates = updatesResponse.updates();
//        for (Update update : updates) {
//            System.out.println(Message message = update.message());
//        }


// async
//        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
////            @Override
//            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
//                List<Update> updates = updatesResponse.updates();
//                for (Update update : updates) {
//                    System.out.println(update.message().toString());
//                }
//            }
//
////            @Override
//            public void onFailure(GetUpdates request, IOException e) {
//
//            }
//        });

    }
}
