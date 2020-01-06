package com.xavier.jdbc.basic.preparedstatement;


import com.xavier.jdbc.basic.Constant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementInQueryStatement {
    public static void main(String[] args) {
        new PreparedStatementInQueryStatement().queryInClause();
    }

    public void queryInClause() {
        String sql = "select id, name, email from users where id in (";
        StringBuilder sqlBuilder = new StringBuilder(sql);

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(3);
        ids.add(4);
        ids.add(5);
        int i= 0;
        for(Integer value : ids) {
            if (i < ids.size()-1)
                sqlBuilder.append("?,");
            else sqlBuilder.append("?)");
            i++;
        }

        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(sqlBuilder.toString())){
            for(int k = 1; k<= ids.size(); k++) {
                psmt.setInt(k, ids.get(k-1));
            }
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("email"));
            }
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
