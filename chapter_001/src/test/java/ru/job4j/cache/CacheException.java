package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.core.Is.is;

public class CacheException {

    @Test
    public void whenThrowException() throws InterruptedException {
        Cache cache = new Cache();
        Base base = new Base(1, "name");
        cache.add(base);
        AtomicReference<Exception> opt = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, "test1"));
                    } catch (Exception e) {
                        opt.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        cache.update(new Base(1, "test2"));
                    } catch (Exception e) {
                        opt.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(opt.get().getMessage(), is("version has already changed"));
    }
}

