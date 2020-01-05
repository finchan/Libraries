package com.xavier.jdbc.basic;

import java.sql.SQLException;

public class Utility {
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.out.println("SQLState: " + ((SQLException)e).getSQLState());
                System.out.println("Error Code: " + ((SQLException)e).getErrorCode());
                System.out.println("Error Code: " + ((SQLException)e).getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
