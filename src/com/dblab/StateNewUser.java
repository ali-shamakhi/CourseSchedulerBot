package com.dblab;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sanei on 10/22/2017.
 */
public class StateNewUser{
    static void validate(int chatID) {

    }
    static void changeStete (int chatID, String state, final Connection connection) throws SQLException {
        connection.createStatement().executeQuery("INSERT INTO user_state_tbl (chat_id, state) VALUES (" + chatID + ",\"" + state + "\")");
    }
}
