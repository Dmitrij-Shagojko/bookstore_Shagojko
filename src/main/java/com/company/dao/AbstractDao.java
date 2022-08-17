package com.company.dao;

import java.util.List;

public interface AbstractDao<K, T> {
    List<T> findAll();

    T findById(K id);

    T create(T entity);

    T update(T entity);

    boolean delete(K id);

    int countAll();
}
