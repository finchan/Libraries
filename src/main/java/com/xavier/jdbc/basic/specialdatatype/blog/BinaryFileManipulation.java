package com.xavier.jdbc.basic.specialdatatype.blog;

import com.xavier.commonsio.utils.Utilities;
import com.xavier.jdbc.basic.Constant;
import com.xavier.jdbc.basic.Utility;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.sql.*;

public class BinaryFileManipulation {
    public static void main(String[] args) {
//        new BinaryFileManipulation().InsertImageIntoDatabase();
        new BinaryFileManipulation().getImageFromDatabase();
    }

    public void InsertImageIntoDatabase() {
        String insert_sql = "insert into images(data) values (?);";
        String fileRelativePath = "rc/jdbc/blob/icon.jpg".replace("/", File.separator);
        String filePath = Utilities.getResourceFileAbsolutePath(fileRelativePath);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(insert_sql)){
            File myFile = new File(filePath);
            try(FileInputStream in = new FileInputStream(filePath)) {
                psmt.setBinaryStream(1, in, myFile.length());
                psmt.executeUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }

    public void getImageFromDatabase() {
        String search_sql = "select data from images limit 1;";
        String fileRelativePath = "rc/output/jdbc/blob/image.jpg".replace("/", File.separator);
        String filePath = Utilities.getResourceFileAbsolutePath(fileRelativePath);
        try(
            Connection connection = DriverManager.getConnection(Constant.JDBC_URL, Constant.USER, Constant.PASSWORD);
            PreparedStatement psmt = connection.prepareStatement(search_sql);
            ResultSet rs = psmt.executeQuery()){
            while (rs.next()) {
                Blob imageBlob = rs.getBlob(1);
                try(FileOutputStream outputFile = new FileOutputStream(filePath)) {
                    IOUtils.copy(imageBlob.getBinaryStream(), new FileOutputStream(filePath));
                }
            }
        } catch (SQLException e) {
            Utility.printSQLException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
