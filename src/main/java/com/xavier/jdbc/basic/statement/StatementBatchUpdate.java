package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class StatementBatchUpdate {
    public static void main(String[] args) {
        new StatementBatchUpdate().batchUpdate();
    }

    public void batchUpdate() {
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.addBatch("update users set name = 'Sam' where id = 5;");
            statement.addBatch("update users set name = 'a' where id = 2;");
            statement.addBatch("update users set name = 'b' where id = 3;");
            statement.addBatch("update users set name = 'c' where id = 4;");
            int[] updateCounts = statement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
