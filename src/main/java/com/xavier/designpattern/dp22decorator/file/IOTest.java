package com.xavier.designpattern.dp22decorator.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Xavier on 2018-02-21.
 */
public class IOTest {
    public static void main(String[] args) throws Exception {
        DataInputStream din = null;
        String basePath = "rc" + File.separator + "designpattern" + File.separator + "ds22decorator";
        String filePath = System.getProperty("user.dir") + File.separator + basePath + File.separator + "IOTest.txt";
        din = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(filePath)));
        byte bs[] = new byte[din.available()];
        din.read(bs);
        String content = new String(bs);
        System.out.println(content);
        din.close();
    }
}
