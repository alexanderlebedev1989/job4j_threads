package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadContent implements IRead {

    @Override
    public String readFile(String content) {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(content))) {
            int data;
            while ((data = i.read()) != -1) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
