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
            count++;
            monitor.notify();
        }
    }

    public void await() {
        synchronized (this) {
            try {
                while (count != total) {
                    System.out.println(String.format("%s wait", Thread.currentThread().getName()));
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

