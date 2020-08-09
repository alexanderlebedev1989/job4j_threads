package ru.job4j.storage;

public class User {

    private int id;
    private int balance;

    public User(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void take(int amount) {
        balance -= amount;
    }
}
