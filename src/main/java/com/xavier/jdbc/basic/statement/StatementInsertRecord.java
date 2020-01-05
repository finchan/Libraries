package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementInsertRecord {
    public static final String INSERT_SQL = "INSERT INTO USERS VALUES (3, 'Pramod', 'pramod@gmail.com', 'India', '123');";

    public static void main(String[] args) {
        new StatementInsertRecord().insertRecord();
    }

    public void insertRecord() {
        System.out.println(INSERT_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            int result = statement.executeUpdate(INSERT_SQL);
            System.out.println("No. of records affected : " + result);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
