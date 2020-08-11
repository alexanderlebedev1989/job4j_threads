package ru.job4j.queue;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void add() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Integer result = queue.poll();
        assertThat(result, is(1));
    }
}
