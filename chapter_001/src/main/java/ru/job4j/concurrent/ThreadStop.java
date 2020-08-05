package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println("start ...");
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().isInterrupted());
                        System.out.println(Thread.currentThread().getState());
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}
