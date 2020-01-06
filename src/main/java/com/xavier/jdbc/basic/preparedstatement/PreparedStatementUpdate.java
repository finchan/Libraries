package com.xavier.jdbc.basic.preparedstatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementUpdate {
    private static final String UPDATE_SQL = "UPDATE USERS " +
            "SET NAME = ? WHERE ID = ?;";

    public static void main(String[] args) {
        new PreparedStatementUpdate().updateRecord();
    }

    public void updateRecord() {
        System.out.println(UPDATE_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(UPDATE_SQL)){
            psmt.setString(1, "NEWNAME");
            psmt.setInt(2, 3);
            System.out.println(psmt);
            psmt.executeUpdate();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
