package ru.job4j.stack;

import org.junit.Test;
import java.util.stream.IntStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    @Test
    public void test() throws InterruptedException {
        CASCount<Integer> casCount = new CASCount<>();
        Thread thread1 = new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> casCount.increment()));
        Thread thread2 = new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> casCount.increment()));
        Thread thread3 = new Thread(() -> IntStream.rangeClosed(1, 20).forEach(i -> casCount.increment()));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        Integer result = casCount.get();
        assertThat(result, is(40));
    }
}
