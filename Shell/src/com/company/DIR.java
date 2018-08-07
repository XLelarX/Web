package com.company;

import java.io.File;

public class DIR {
    private static File file = new File("");

    DIR() {
        file = new File(file.getAbsolutePath());
    }

    public static String getPath() {
        return file.getAbsolutePath();
    }

    static File getFile() {
        return file;
    }

    static void setFile(File f) {
        file = f;
    }
}