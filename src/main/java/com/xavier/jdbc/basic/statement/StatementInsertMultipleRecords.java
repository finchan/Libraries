package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementInsertMultipleRecords {
    public static final String INSERT_MULTIPLE_RECORDS = "INSERT INTO USERS VALUES  " +
            "(4, 'Deepa', 'deepa@gmail.com', 'India', '123'), " +
            "(5, 'Tom', 'top@gmail.com', 'India', '123');";

    public static void main (String[] args) {
        new StatementInsertMultipleRecords().insertMultipleRecords();
    }

    public void insertMultipleRecords () {
        System.out.println(INSERT_MULTIPLE_RECORDS);

        try (
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            int result = statement.executeUpdate(INSERT_MULTIPLE_RECORDS);
            System.out.println("No. of records affected : " + result);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
