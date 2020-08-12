package ru.job4j.wait;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        CountBarrier barrier = new CountBarrier(count);
        Thread first = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                    barrier.count();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread second = new Thread(() -> {
          for (int i = 0; i < count; i++) {
              barrier.await();
              System.out.println(String.format("%s work", Thread.currentThread().getName()));
          }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
