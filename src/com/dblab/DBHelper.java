package com.dblab;

import com.dblab.state.StateNewUser;

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

    /**
     * @param userID
     * @throws SQLException if student already exists (duplicate UserID)
     */
    static void createNewStudent(int userID) throws SQLException {
        _con.createStatement().execute("INSERT INTO student (UserID, State, RegistrationDate) VALUES (" + userID + ", \"" + StateNewUser.VALUE + "\", NOW())");
    }

    public static final String FIELD_UserID = "UserID";
    public static final String FIELD_State = "State";
    public static final String FIELD_RegistrationDate = "RegistrationDate";
    public static final String FIELD_FirstName = "FirstName";
    public static final String FIELD_LastName = "LastName";

    public static final String FIELD_MajorID = "MajorID";
    public static final String FIELD_MajorName = "UserID";
    public static final String FIELD_EntranceYear = "State";

    private static boolean isStringType(String field) {
        if (field.equals(FIELD_UserID)) return false;
        else if (field.equals(FIELD_State)) return true;
        else if (field.equals(FIELD_MajorID)) return false;
        else if (field.equals(FIELD_RegistrationDate)) return true;
        else if (field.equals(FIELD_FirstName)) return true;
        else if (field.equals(FIELD_LastName)) return true;
        else if (field.equals(FIELD_MajorName)) return true;
        else if (field.equals(FIELD_EntranceYear)) return false;
        else return false;
    }

    static void setStudentState(int userID, String state) throws SQLException {
        setStudentField(userID, FIELD_State, state);
    }

    /**
     * Sets <code>field</code> in student table by <code>value</code>, for user with <code>userID</code>
     * @param userID
     * @param field The field to be set. Use <code>DBHelper.FIELD_*</code> values.
     * @param value
     * @throws SQLException
     */
    static void setStudentField(int userID, String field, Object value) throws SQLException {
        if (isStringType(field)) {
            _con.createStatement().execute("UPDATE student SET " + field + " = \"" + value + "\" WHERE UserID = " + userID);
        } else {
            _con.createStatement().execute("UPDATE student SET " + field + " = " + value + " WHERE UserID = " + userID);
        }
    }

    /**
     * Sets <code>field</code> in major table by <code>value</code>, for user with <code>majorID</code>
     * @param majorID
     * @param field The field to be set. Use <code>DBHelper.FIELD_*</code> values.
     * @param value
     * @throws SQLException
     */
    static void setMajorField(int majorID, String field, Object value) throws SQLException {
        if (isStringType(field)) {
            _con.createStatement().execute("UPDATE major SET " + field + " = \"" + value + "\" WHERE MajorID = " + majorID);
        } else {
            _con.createStatement().execute("UPDATE major SET " + field + " = " + value + " WHERE MajorID = " + majorID);
        }
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
