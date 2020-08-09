package ru.job4j.io;

public class Worker {

    private IRead read;
    private IWrite write;
    private String context;

    public Worker(IRead read, IWrite write, String context) {
        this.read = read;
        this.write = write;
        this.context = context;
    }

    public void writeText() {
        synchronized (this) {
            write.writeFile(context);
        }
    }
}
