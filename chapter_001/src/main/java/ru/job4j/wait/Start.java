package ru.job4j.wait;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(10);
        Thread first = new Thread(
                () -> {
                    try {
                        barrier.count();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread second = new Thread(
                barrier::await
        );
        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Потоки завершили работу");
    }
}
