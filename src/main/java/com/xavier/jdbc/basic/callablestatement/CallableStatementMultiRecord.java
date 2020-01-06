package com.xavier.jdbc.basic.callablestatement;

import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;

import java.sql.*;

public class CallableStatementMultiRecord {
    public static void main(String[] args) {
        new CallableStatementMultiRecord().callStoreProcedure();
    }
    public void callStoreProcedure() {
        String callProcedureSQL = "call retrieve_users()";
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            CallableStatement csmt = connection.prepareCall(callProcedureSQL);
            ResultSet rs = csmt.executeQuery()){
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "\t" + name + "\t" + email + "\t" + country + "\t" + password);
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
}
