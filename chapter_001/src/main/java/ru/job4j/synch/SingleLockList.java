package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    private SimpleArray<T> list = new SimpleArray<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return list.createCopy().iterator();
    }
}
