package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(final int limit) {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (queue.size() == limit) {
                this.wait();
            }
            queue.offer(value);
            System.out.println("Add element: " + value);
            System.out.println("Queue size is: " + queue.size());
            this.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            if (queue.size() == 0) {
                this.wait();
            }
            T value = queue.poll();
            this.notify();
            return value;
        }
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
