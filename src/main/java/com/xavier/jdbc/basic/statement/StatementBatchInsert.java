package com.xavier.jdbc.basic.statement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class StatementBatchInsert {

    public static void main(String[] args) {
        new StatementBatchInsert().batchInsert();
    }

    public void batchInsert() {
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            Statement statement = connection.createStatement()){
            //The reason setAutoCommit as false to simulate rollback operation!!!!
            connection.setAutoCommit(false);
            statement.addBatch("INSERT INTO Users VALUES (7, 'Pramod', 'pramod@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (3, 'A', 'a@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (6, 'D', 'd@gmail.com', 'India', '123');");
            int[] updateCounts = statement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
