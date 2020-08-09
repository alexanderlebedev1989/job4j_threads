package ru.job4j;

public class Start {
    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(4);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " старт");
                },
                "Первая нить"
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        barrier.count();
                        barrier.await();
                        System.out.println(Thread.currentThread().getName() + " старт");
                    }
                },
                "Вторая нить"
        );
        first.start();
        second.start();
    }
}
