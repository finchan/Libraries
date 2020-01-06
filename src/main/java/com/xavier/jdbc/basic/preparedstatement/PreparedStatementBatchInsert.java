package com.xavier.jdbc.basic.preparedstatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class PreparedStatementBatchInsert {
    public static void main(String[] args) {
        new PreparedStatementBatchInsert().batchInsert();
    }

    public void batchInsert() {
        String batchInsertSQL = "insert into users (id, name, email, country, password) values (?, ?, ?, ?, ?)";

        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(batchInsertSQL)) {
            connection.setAutoCommit(false);
            psmt.setInt(1, 7);
            psmt.setString(2, "E2");
            psmt.setString(3, "e2@abc.com");
            psmt.setString(4, "E2country");
            psmt.setString(5, "E2password");
            psmt.addBatch();

            psmt.setInt(1, 8);
            psmt.setString(2, "F2");
            psmt.setString(3, "f2@abc.com");
            psmt.setString(4, "F2country");
            psmt.setString(5, "F2password");
            psmt.addBatch();
            int insertUpdates [] = psmt.executeBatch();
            System.out.println(Arrays.toString(insertUpdates ));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
