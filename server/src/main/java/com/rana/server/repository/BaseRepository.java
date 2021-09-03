package com.rana.server.repository;

import com.rana.server.entity.BasicEntity;

import java.util.Map;

public class BaseRepository<T extends BasicEntity> {

    private Map<Long, T> entries;

    public void add(T entry) {
        entries.put(entry.getId(), entry);
    }

    public T get(long id) {
        return entries.get(id);
    }

    public void remove(long id) {
        entries.remove(id);
    }

    public boolean contains(long id) {
        return entries.containsKey(id);
    }

}
