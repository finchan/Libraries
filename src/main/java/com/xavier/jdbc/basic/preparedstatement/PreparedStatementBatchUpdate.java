package com.xavier.jdbc.basic.preparedstatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class PreparedStatementBatchUpdate {
    public static void main(String[] args) {
        new PreparedStatementBatchUpdate().batchUpdate();
    }

    public void batchUpdate() {
        String batchInsertSQL = "update users set name = ? where id = ?";

        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(batchInsertSQL)) {
            connection.setAutoCommit(false);
            psmt.setString(1, "A");
            psmt.setInt(2, 2);
            psmt.addBatch();

            psmt.setString(1, "B");
            psmt.setInt(2, 3);
            psmt.addBatch();

            psmt.setString(1, "C");
            psmt.setInt(2, 4);
            psmt.addBatch();
            int updateCounts [] = psmt.executeBatch();
            System.out.println(Arrays.toString(updateCounts ));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
