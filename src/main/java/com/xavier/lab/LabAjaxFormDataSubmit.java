package com.xavier.lab;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Xavier on 2017/10/9.
 */
public class LabAjaxFormDataSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String formDataValue = "";
        if (req.getHeader("Content-Type").toLowerCase().indexOf("x-www-form-urlencoded") != -1) {
            //Form Data
            formDataValue = req.getParameter("formdatainput");
        } else {
            //Payload Data
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            char[] buff = new char[0124];
            int len = -1;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            reader.close();
            formDataValue = sb.toString();
        }
        System.out.println(formDataValue);
    }
}
