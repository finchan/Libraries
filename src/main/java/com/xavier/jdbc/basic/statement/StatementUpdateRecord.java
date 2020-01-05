package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementUpdateRecord {
    private static final String UPDATE_SQL = "UPDATE USERS SET `NAME`='Xavier' WHERE ID = 3;";

    public static void main(String[] args) {
        new StatementUpdateRecord().updateRecord();
    }

    public void updateRecord () {
        System.out.println(UPDATE_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            int result = statement.executeUpdate(UPDATE_SQL);
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
