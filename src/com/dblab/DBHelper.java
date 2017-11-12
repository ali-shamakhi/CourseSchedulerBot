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
                _con = DriverManager.getConnection(LocalConfigs.CONNECTION_STRING, LocalConfigs.USERNAME, LocalConfigs.PASSWORD);
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
    public static final String FIELD_Substate = "Substate";
    public static final String FIELD_RegistrationDate = "RegistrationDate";
    public static final String FIELD_FirstName = "FirstName";
    public static final String FIELD_LastName = "LastName";

    public static final String FIELD_MajorID = "MajorID";
    public static final String FIELD_MajorName = "MajorName";
    public static final String FIELD_University = "University";
    public static final String FIELD_EntranceYear = "EntranceYear";

    private static boolean isStringType(String field) {
        if (field.equals(FIELD_UserID)) return false;
        else if (field.equals(FIELD_State)) return true;
        else if (field.equals(FIELD_Substate)) return true;
        else if (field.equals(FIELD_RegistrationDate)) return true;
        else if (field.equals(FIELD_FirstName)) return true;
        else if (field.equals(FIELD_LastName)) return true;
        else if (field.equals(FIELD_MajorID)) return false;
        else if (field.equals(FIELD_MajorName)) return true;
        else if (field.equals(FIELD_University)) return true;
        else if (field.equals(FIELD_EntranceYear)) return false;
        else throw new RuntimeException("isStringType(field): Unknown field " + field);
    }

    public static void setStudentState(int userID, String state) throws SQLException {
        setStudentField(userID, FIELD_State, state);
    }

    public static void setStudentSubstate(int userID, String substate) throws SQLException {
        setStudentField(userID, FIELD_Substate, substate);
    }

    /**
     * Sets <code>field</code> in student table by <code>value</code>, for user with <code>userID</code>
     * @param userID
     * @param field The field to be set. Use <code>DBHelper.FIELD_*</code> values.
     * @param value
     * @throws SQLException
     */
    public static void setStudentField(int userID, String field, Object value) throws SQLException {
        if (value == null) {
            _con.createStatement().execute("UPDATE student SET " + field + " = NULL WHERE UserID = " + userID);
        }
        if (isStringType(field)) {
            _con.createStatement().execute("UPDATE student SET " + field + " = \"" + value + "\" WHERE UserID = " + userID);
        } else {
            _con.createStatement().execute("UPDATE student SET " + field + " = " + value + " WHERE UserID = " + userID);
        }
    }

    public static Object getStudentField(int userID, String field) throws SQLException {
        ResultSet rs = _con.createStatement().executeQuery("SELECT " + field + " FROM student WHERE UserID = " + userID);
        Object result = null;
        while (rs.next()) {
            if (isStringType(field)) {
                result = rs.getString(field);
            }
            else {
                result = rs.getInt(field);
            }
            break;
        }
        return result;
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

    public static Object getMajorField(int majorID, String field) throws SQLException {
        ResultSet rs = _con.createStatement().executeQuery("SELECT " + field + " FROM major WHERE MajorID = " + majorID);
        Object result = null;
        while (rs.next()) {
            if (isStringType(field)) {
                result = rs.getString(field);
            }
            else {
                result = rs.getInt(field);
            }
            break;
        }
        return result;
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
