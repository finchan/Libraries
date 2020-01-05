package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.*;

public class StatementQuery {
    private static final String QUERY = "select id,name,email,password,country from users;";

    public static void main(String[] args) {
        new StatementQuery().getRecords();
    }

    public void getRecords() {
        System.out.println(QUERY);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUERY)){
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
//                String country = rs.getString("country");
                //Index starts from 1, and this is corresponding to SQL Query statements.
                String country = rs.getString(5);
                String password = rs.getString("password");
                System.out.println(id + ", " + name + ", " + email + ", " + country + ", " + password);
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
