package ru.job4j.cache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    public void update(Base model) throws OptimisticException {
        cache.computeIfPresent(model.getId(), (id, data) -> {
            if (data.getVersion() != model.getVersion()) {
                throw new OptimisticException("Version has already changed");
            }
            data.changeVersion(data.getVersion() + 1);
            data.setName(model.getName());
            return data;
        });
    }

    public void delete(Base model) {
        cache.remove(model.getId(), model);
    }
}
