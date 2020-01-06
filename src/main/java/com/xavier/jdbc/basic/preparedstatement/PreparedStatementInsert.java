package com.xavier.jdbc.basic.preparedstatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementInsert {
    private static final String INSERT_SQL = "INSERT INTO USERS " +
            "(ID, NAME, EMAIL, COUNTRY, PASSWORD) VALUES (?, ?, ?, ?, ?);";

    public static void main(String[] args) {
        new PreparedStatementInsert().insertRecord();
    }

    public void insertRecord() {
        System.out.println(INSERT_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(INSERT_SQL)){
            psmt.setInt(1, 9);
            psmt.setString(2, "Xavier");
            psmt.setString(3, "abc@abc.com");
            psmt.setString(4, "China");
            psmt.setString(5, "123");
            System.out.println(psmt);
            psmt.executeUpdate();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
