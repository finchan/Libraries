package com.xavier.commonsio.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class Utilities {

    public static String getResourceFileAbsolutePath(String resourceRelativePath) {
        String[] folders = resourceRelativePath.split("//");
        if (folders.length == 1) {
            return System.getProperty("user.dir") + File.separator + folders[0];
        } else {
            String path = System.getProperty("user.dir") + File.separator;
            for (String folder : folders) {
                path += folder;
            }
            return path.substring(0, path.length() - 2);
        }
    }

    public static String getNewFileName(String filePath, String suffix) {
        String fileName = FilenameUtils.getName(filePath);
        String fileBaseName = FilenameUtils.getBaseName(fileName);
        String fileExtension = FilenameUtils.getExtension(fileName);
        String fileOutputPath = FilenameUtils.getFullPath(filePath) +
                File.separator + fileBaseName +
                "_" + suffix +
                FilenameUtils.EXTENSION_SEPARATOR_STR + fileExtension;
        return fileOutputPath;
    }
}
