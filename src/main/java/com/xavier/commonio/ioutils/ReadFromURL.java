package com.xavier.commonio.ioutils;

import org.apache.commons.io.IOUtils;
import java.net.URI;
import java.nio.charset.Charset;

public class ReadFromURL {
    public static void main(String[] args) {
        try {
            String content = IOUtils.toString(URI.create("http://commons.apache.org"), Charset.forName("iso-8859-1"));
            System.out.println(content);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
