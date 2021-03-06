package ru.job4j.concurrent;

public class Loading {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("Start loading ... ");
                            for (int i = 0; i < 100; i++) {
                                System.out.print("\rLoading : " + i + "%");
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread.start();
        System.out.println("Main");
        Thread.sleep(1000);
        thread.interrupt();
    }
}

