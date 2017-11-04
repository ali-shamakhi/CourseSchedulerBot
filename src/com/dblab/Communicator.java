package com.dblab;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;
import java.util.IllegalFormatException;

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
