package ru.job4j.cash;

import java.util.concurrent.ConcurrentHashMap;

public class Cash {

    private final ConcurrentHashMap<Integer, Base> storageCash = new ConcurrentHashMap<>();

    public void add(Base model) {
        storageCash.put(model.getId(), model);
    }

    public void update(Base model) throws OptimisticException {
        storageCash.computeIfPresent(model.getId(), (id, data) -> {
            if (data.getVersion() != model.getVersion()) {
                throw new OptimisticException("version has already changed");
            }
            data.changeVersion();
            data.setName(model.getName());
            return model;
        });
    }

    public void delete(Base model) {
        storageCash.remove(model.getId(), model);
    }

    public ConcurrentHashMap<Integer, Base> getStorageCash() {
        return storageCash;
    }
}
