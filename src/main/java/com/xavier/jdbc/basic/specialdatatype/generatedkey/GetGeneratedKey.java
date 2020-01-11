package com.xavier.jdbc.basic.specialdatatype.generatedkey;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.*;

public class GetGeneratedKey {
    public static void main(String[] args) {
        new GetGeneratedKey().getGeneratedKey();
    }

    public void getGeneratedKey() {
        String insert_sql = "insert into students (name) values (?);";
        try(Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD)) {
            //Step 1: Disable auto commit
            connection.setAutoCommit(false);
            try (PreparedStatement psmt = connection.prepareStatement(insert_sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, "Xavier");
                psmt.executeUpdate();
                psmt.setString(1, "Javier");
                psmt.executeUpdate();
                // STEP 2 - Commit all these inserts
                connection.commit();
                System.out.println("Transaction is committed successfully.");
                try (ResultSet resultSet = psmt.getGeneratedKeys()) {
                    if(resultSet.first()) {
                        System.out.println(resultSet.getLong(1));
                    }
                }
            } catch (SQLException e) {
                Utility.printSQLException(e);
                if(connection != null) {
                    try{
                        System.out.println("Transaction is being rolled back.");
                        connection.rollback();
                    } catch(SQLException e1) {
                        Utility.printSQLException(e1);
                    }
                }
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
