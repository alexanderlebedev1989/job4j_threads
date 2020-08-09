package ru.job4j.io;

import java.io.File;

public class ParseFile {
    public static void main(String[] args) throws InterruptedException {

        String content1 = "content.txt";
        String content2 = "content_new.txt";
        File file = new File("test.txt");

        IRead read = new ReadContent();
        IWrite write = new WriteContent(read, file);

        Worker worker1 = new Worker(read, write, content1);
        Worker worker2 = new Worker(read, write, content2);

        Thread thread1 = new Thread(worker1::writeText);
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker2.writeText();
        });
        System.out.println("Workers starting...");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Workers finished...");
    }
}

