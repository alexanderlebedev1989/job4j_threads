package ru.job4j.cache;

public class Base {
    private int id;
    private String name;
    private int version;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void changeVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
