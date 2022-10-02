package com.solvd.dao.interfaces;

import java.util.List;

public interface BaseDAO <T> {
    int add(T object);
    void update (T t);
    void delete (int id);
    T getById (int id);
    List<T> getAll();
}