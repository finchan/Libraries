package com.xavier.commonsio.ioutils;

import com.xavier.commonsio.utils.Utilities;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        String textFilePath = Utilities.getResourceFileAbsolutePath("/rc/commonsio/test.txt");
        String binaryFilePath = Utilities.getResourceFileAbsolutePath("/rc/commonsio/image.jpg");
        copyFile(textFilePath, "copied");
        copyFile(binaryFilePath, "copied");
    }

    //Copy Text file or Binary File
    public static void copyFile(String filePath, String suffix) throws IOException {
        String outputFilePath = Utilities.getNewFileName(filePath, suffix);
        try(InputStream in = new FileInputStream(new File(filePath));
            OutputStream out = new FileOutputStream(new File(outputFilePath));) {
            IOUtils.copy(in, out);
        }
    }
}
