package ru.job4j.cash;

public class Base {
    private int id;
    private String name;
    private int version;

    public Base(int id, String name, int version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void changeVersion() {
        version++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
