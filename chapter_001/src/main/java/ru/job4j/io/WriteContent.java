package ru.job4j.io;

import java.io.*;

public class WriteContent implements IWrite {

    private IRead read;
    private File file;

    public WriteContent(IRead read, File file) {
        this.read = read;
        this.file = file;
    }
    @Override
    public void writeFile(String content) {
        String str = read.readFile(content);
        try (PrintWriter o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file, true)))) {
            o.write(str + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
