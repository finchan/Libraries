package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementCreateTable {
    private static final String createTableSQL = "" +
            "create table users(\n" +
            "     id  int(3) primary key,\n" +
            "     name varchar(20),\n" +
            "     email varchar(20),\n" +
            "     country varchar(20),\n" +
            "     password varchar(20)\n" +
            "  );";

    public static void main(String[] args) {
        new StatementCreateTable().createTable();
    }

    public void createTable() {
        System.out.println(createTableSQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
