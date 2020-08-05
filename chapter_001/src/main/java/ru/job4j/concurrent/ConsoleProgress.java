package ru.job4j.concurrent;

import org.w3c.dom.ls.LSOutput;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
            try {
                System.out.println("Loading...");
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(500);
                    System.out.print("\rload: " + "\\");
                    Thread.sleep(500);
                    System.out.print("\rload: " + "|");
                    Thread.sleep(500);
                    System.out.print("\rload: " + "/");
                }
            } catch (InterruptedException e) {
                System.out.println();
                System.out.println("Работа нити прервана");
            }
        }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }
}
