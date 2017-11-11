package com.dblab.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Maps UserID to a data model
 * @param <T> type of data model
 */
public class UserMap<T> implements Map<Integer, T> {

    private Map<Integer, T> _map;

    public UserMap() {
        _map = new HashMap<Integer, T>();
    }

    public int size() {
        return _map.size();
    }

    public boolean isEmpty() {
        return _map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return _map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return _map.containsValue(value);
    }

    public T get(Object key) {
        return _map.get(key);
    }

    public T put(Integer key, T value) {
        return _map.put(key, value);
    }

    public T remove(Object key) {
        return _map.remove(key);
    }

    public void putAll(Map<? extends Integer, ? extends T> m) {
        _map.putAll(m);
    }

    public void clear() {
        _map.clear();
    }

    public Set<Integer> keySet() {
        return _map.keySet();
    }

    public Collection<T> values() {
        return _map.values();
    }

    public Set<Entry<Integer, T>> entrySet() {
        return _map.entrySet();
    }
}
