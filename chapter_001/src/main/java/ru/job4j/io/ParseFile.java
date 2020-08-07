package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private volatile File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            byte[] dataBuffer = new byte[1024];
            int data;
            while ((data = i.read(dataBuffer, 0, 1024)) != -1) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public String getContentWithoutUnicode() {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            byte[] dataBuffer = new byte[1024];
            int data;
            while ((data = i.read(dataBuffer, 0, 1024)) != -1) {
                if (data < 0x80) {
                    builder.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void saveContent(String content) {
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(content));
             BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] dataBuffer = new byte[1024];
            int data;
            while ((data = i.read(dataBuffer, 0, 1024)) != -1) {
                o.write(dataBuffer, 0, data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
