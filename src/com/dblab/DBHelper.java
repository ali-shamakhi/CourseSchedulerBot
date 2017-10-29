package com.dblab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

    private static Connection _con = null;

    static void init() {
        checkConnection();
    }

    private static void checkConnection() {
        if (_con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                _con = DriverManager.getConnection("jdbc:mysql://localhost/dblab_log", LocalConfigs.USERNAME, LocalConfigs.PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(2);
            }
        }
    }

    static void setStudentState(int userID, String state) throws SQLException {
        _con.createStatement().execute("INSERT INTO student (UserID, State) VALUES (" + userID + ", \"" + state + "\")" +
                "ON DUPLICATE KEY UPDATE State = \"" + state + "\"");
    }

    static String getStudentState(int userID) throws SQLException {
        ResultSet result = _con.createStatement().executeQuery("SELECT State FROM student WHERE UserID = " + userID);
        String state = null;
        while (result.next()) {
            state = result.getString("State");
            break;
        }
        return state;
    }
}
