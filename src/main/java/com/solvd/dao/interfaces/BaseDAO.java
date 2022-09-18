package com.solvd.dao.interfaces;

import javax.management.AttributeNotFoundException;
import java.util.List;

public interface BaseDAO <T> {
    void add(T object);
    void update (int id, T t);
    void delete (int id);
    T getById (int id);
    List<T> getAll();
}