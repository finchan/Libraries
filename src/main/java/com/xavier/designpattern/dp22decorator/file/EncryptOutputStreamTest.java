package com.xavier.designpattern.dp22decorator.file;

import java.io.*;

/**
 * Created by Xavier on 2018-02-21.
 */
public class EncryptOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String basePath = System.getProperty("user.dir") + File.separator
                +  "rc" + File.separator + "designpattern" + File.separator
                + "ds22decorator"+File.separator;
        DataOutputStream dout = new DataOutputStream(
                new BufferedOutputStream(
                        new EncryptOutputStream(
                                new FileOutputStream(basePath+"MyEncrypt.txt")
                        )
                )
        );
        dout.write("abcde".getBytes());
        dout.close();
    }
}
