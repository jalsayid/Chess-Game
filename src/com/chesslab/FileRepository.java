package com.chesslab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepository {

    public boolean save(String path, String content) {
        try {
            File f = new File(path);
            String dir = f.getParent();
            if (dir != null) {
                new File(dir).mkdirs();
            }
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(content);
                fw.flush();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String load(String path) {
        // minimal loader - left intentionally short
        return "";
    }
}
