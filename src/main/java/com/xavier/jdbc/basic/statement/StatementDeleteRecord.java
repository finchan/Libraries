package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDeleteRecord {
    private static final String DELETE_USER_SQL = "delete from users where id = 3;";

    public static void main(String[] args) {
        new StatementDeleteRecord().deleteRecord();
    }

    public void deleteRecord() {
        System.out.println(DELETE_USER_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            int result = statement.executeUpdate(DELETE_USER_SQL);
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
