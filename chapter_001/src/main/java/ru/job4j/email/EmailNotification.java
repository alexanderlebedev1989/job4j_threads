package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService = Executors.newFixedThreadPool(size);

    public void emailTo(User user) {
        executorService.submit(() -> {
            send(subject(user), body(user), user.getEmail());
        });
    }

    public void close() {
        executorService.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    private String subject(User user) {
        return new StringBuilder()
                .append("Notification ")
                .append(user.getUsername())
                .append(" to")
                .append(user.getEmail())
                .toString();
    }

    private String body(User user) {
        return new StringBuilder()
                .append("Event for")
                .append(user.getUsername())
                .toString();
    }
}
