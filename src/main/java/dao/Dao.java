package dao;

import java.util.List;

public interface Dao<T> {
    void add(T item);
    void remove(int id);
    T get(int id);
    List<T> getAll();
}
