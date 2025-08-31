package com.solvd.hospital.repository;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T entity) {
        items.add(entity);
    }

    public boolean remove(T entity) {
        return items.remove(entity);
    }

    public List<T> findAll() {
        return new ArrayList<>(items);
    }
}
