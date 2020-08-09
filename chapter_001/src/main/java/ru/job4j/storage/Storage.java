package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class Storage {

    private final List<User> users = new ArrayList<>();

    public boolean add(User user) {
        boolean result = false;
        synchronized (this) {
            users.add(user);
            result = true;
        }
        return result;
    }

    public boolean update(int id, User user) {
        boolean result = false;
        synchronized (this) {
            users.set(id, user);
            result = true;
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        synchronized (this) {
            users.remove(user);
            result = true;
        }
        return result;
    }

    public void transfer(User user1, User user2, int amount) {
        synchronized (this) {
            user1.take(amount);
            user2.deposit(amount);
        }
    }
}
