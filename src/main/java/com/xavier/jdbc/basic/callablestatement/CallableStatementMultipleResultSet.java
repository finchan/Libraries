package com.xavier.jdbc.basic.callablestatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.*;

public class CallableStatementMultipleResultSet {
    private static final String STORE_PROCEDURE_SQL = "call retreive_different_results()";

    public static void main(String[] args) {
        new CallableStatementMultipleResultSet().retrieveMultipleResultSets();
    }

    public void retrieveMultipleResultSets() {
        try (
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            CallableStatement csmt = connection.prepareCall(STORE_PROCEDURE_SQL)) {
            /**
             * The execute method returns a boolean to
             * indicate the form of the first result.  You must call either the method
             * getResultSet or getUpdateCount
             * to retrieve the result; you must call getMoreResults to
             * move to any subsequent result(s).
             */
            boolean hasRS = csmt.execute();
            if(hasRS) {
                try(ResultSet rs = csmt.getResultSet()){
                    while(rs.next()) {
                        System.out.println("NAME= " + rs.getString(1));
                    }
                }
            }
            //Get More ResultSet
            if(csmt.getMoreResults()) {
                try(ResultSet rs = csmt.getResultSet()) {
                    while(rs.next()) {
                        System.out.println("Email = " + rs.getString(1));
                    }
                }
            }
            //Get More ResultSet
            if (csmt.getMoreResults()) {
                try (ResultSet rs = csmt.getResultSet()) {
                    if (rs.next()) {
                        System.out.println("Users count = " + rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
