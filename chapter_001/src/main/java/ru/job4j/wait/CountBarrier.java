package ru.job4j.wait;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() throws InterruptedException {
        synchronized (monitor) {
            System.out.println("Первый поток проснулся");
            while (true) {
                System.out.println("Counter is: " + count++);
                if (count == total) {
                    monitor.notify();
                    break;
                }
            }
            Thread.sleep(1000);
        }
    }

    public void await() {
        synchronized (this) {
            while (true) {
                if (count != total) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Total equally counter");
                    System.out.println("Второй поток проснулся");
                    break;
                }
            }
        }
    }
}

