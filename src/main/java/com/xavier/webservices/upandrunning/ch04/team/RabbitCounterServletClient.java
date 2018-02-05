package com.xavier.webservices.upandrunning.ch04.team;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier on 2018/2/5.
 */
public class RabbitCounterServletClient {
    private static final String url = "http://localhost:8080/libraries/fib";
    
    public static void main(String[] args) {
        new RabbitCounterServletClient().send_request();
    }

    private void send_request() {
        try{
            HttpURLConnection conn = null;

            //POST request
            List<Integer> nums = new ArrayList<Integer>();
            for (int i = 1; i< 15; i++) nums.add(i);
            String payload = URLEncoder.encode("nums", "UTF-8") + "=" +
                    URLEncoder.encode((nums.toString()), "UTF-8");
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/xml");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();
            get_response(conn);

            //GET request
            conn = get_connection(url, "GET");
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);

            conn = get_connection(url + "?num=12", "GET");
            conn.addRequestProperty("accept", "text/plain");
            conn.connect();
            get_response(conn);

            //DELETE request
            conn = get_connection(url + "?num=12", "DELETE");
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);

            //GET request
            conn = get_connection(url + "?num=12", "GET");
            conn.addRequestProperty("accept", "text/html");
            conn.connect();
            get_response(conn);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void get_response(HttpURLConnection conn) {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader((new InputStreamReader(conn.getInputStream())));
            String next = null;
            while((next = reader.readLine()) != null) {
                xml += next;
            }
            System.out.println("The reponse: \n" + xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpURLConnection get_connection(String url_string, String verb) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_string);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(verb);
            conn.setDoInput(true);
            conn.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
