package com.xavier.jdbc.basic.preparedstatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.*;

public class PreparedStatementQuery {
    private static final String QUERY_SQL= "SELECT * FROM USERS WHERE NAME = ?;";

    public static void main(String[] args) {
        new PreparedStatementQuery().getRecords();
    }

    public void getRecords () {
        System.out.println(QUERY_SQL);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(QUERY_SQL)){
            psmt.setString(1, "Deepa");
            try(ResultSet rs = psmt.executeQuery()){
                while(rs.next()){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columns = rsmd.getColumnCount();
                    for (int i = 1; i <= columns; i++) {
                        if (i == 1) {
                            System.out.print(rsmd.getColumnName(i) + ": " + rs.getInt(i) +"\t\t");
                        } else {
                            System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + "\t\t");
                        }
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
