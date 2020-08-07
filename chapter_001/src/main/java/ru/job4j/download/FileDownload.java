package ru.job4j.download;

public class FileDownload {
    public static void main(String[] args) {
        Loader loader = new Loader();
        loader.loadFile(args[0], args[1]);
    }
}
