package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);


    private void init() {
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable job = tasks.poll();
                        job.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (tasks.isEmpty()) {
            init();
        }
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
