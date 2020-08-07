package ru.job4j.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Loader {

    private static final long MILLISECOND = 1000;

    public void loadFile(String fileName, String speed) {
        int bytesSecondLimit = Integer.parseInt(speed);
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileName).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long downloadedBytes = 0;
            long startTime = System.currentTimeMillis();
            long endTime = 0;
            long addSecond = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                endTime = System.currentTimeMillis() - startTime + endTime + addSecond;
                downloadedBytes += bytesRead;
                long bytesSecond = downloadedBytes / endTime / MILLISECOND;
                addSecond = 0;
                if (bytesSecond > bytesSecondLimit) {
                    Thread.sleep(1000);
                    addSecond = 1000;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
