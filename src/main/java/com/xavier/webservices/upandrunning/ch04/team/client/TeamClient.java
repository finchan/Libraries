package com.xavier.webservices.upandrunning.ch04.team.client;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Xavier on 2017/12/22.
 */
public class TeamClient {
    private static final String endpoint = "http://localhost:8888/teams";

    public static void main(String[] args) {
        new TeamClient().send_requests();
    }

    private void send_requests() {
        try {
            HttpURLConnection conn = null;
            conn = get_connection(endpoint, "GET");
            conn.connect();
            print_and_parse(conn, true);

            conn = get_connection(endpoint + "?name=BurnsAndAllen", "GET");
            conn.connect();
            print_and_parse(conn, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print_and_parse(HttpURLConnection conn, boolean parse) {
        try{
            String xml = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while((next = reader.readLine()) != null) {
                xml += next;
            }
            System.out.println("The raw XML: " + xml);

            if(parse) {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(new ByteArrayInputStream(xml.getBytes()), new SaxParserHandler());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    static class SaxParserHandler extends DefaultHandler{
        char[] buffer = new char[1024];
        int n = 0;

        public void startElement(String uri, String lname, String qname, Attributes attributes) {
            clear_buffer();
        }

        private void clear_buffer() {
            Arrays.fill(buffer, '\0');
            n = 0;
        }

        public void characters(char[] data, int start, int length) {
            System.arraycopy(data, start, buffer, 0, length);
            n+=length;
        }

        public void endElement(String uri, String lname, String qname) {
            if(Character.isUpperCase(buffer[0]))
                System.out.println(new String(buffer));
            clear_buffer();
        }
    }

    private HttpURLConnection get_connection(String url_string, String verb) {
        HttpURLConnection conn = null;
        try{
            URL url = new URL(url_string);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(verb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
