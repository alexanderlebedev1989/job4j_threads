package ru.job4j.multithreads;


public class MasterSlaveBarrier {

    private int count = 0;

    public void tryMaster() throws InterruptedException {
        synchronized (this) {
            if (count == 0) {
                count++;
                System.out.println("Thread A");
                notify();
            }
            wait();
        }
        Thread.sleep(1000);
    }

    public void trySlave() throws InterruptedException {
        synchronized (this) {
            if (count == 1) {
                count--;
                System.out.println("Thread B");
                notify();
            }
            wait();
        }
        Thread.sleep(1000);
    }
}
