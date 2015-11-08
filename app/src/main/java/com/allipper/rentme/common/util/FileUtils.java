package com.allipper.rentme.common.util;

import java.io.File;

/**
 * Created by allipper on 2015-11-05.
 */
public class FileUtils {

    public static String getFileType(File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        return fileType;
    }
}
