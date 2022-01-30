package com.thiennam.messtar.util;

public class ResourceUtil {
    public static String getFileExt(String fileName) {
        String ext = "";
        if (fileName != null) {
            String[] parts = fileName.split("\\.");
            if (parts.length > 0) {
                ext = parts[parts.length - 1];
            }
        }
        return ext;
    }
}
