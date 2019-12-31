package com.xavier.commonsio.ioutils;

import com.xavier.commonsio.utils.Utilities;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadTextLineByLine {
    public static void main(String[] args) throws IOException {
        String filePath = Utilities.getResourceFileAbsolutePath("/rc/commonsio/test.txt");
        readUsingTraditionalWay(filePath);
        readUsingIOUtils(filePath);
    }

    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void readUsingTraditionalWay(String filePath) throws IOException {
        try (BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(filePath))))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void readUsingIOUtils (String filePath) throws IOException {
        try(InputStream in = new FileInputStream(filePath)) {
            System.out.println(IOUtils.toString(in, "UTF-8"));
        }
    }
}
