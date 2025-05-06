package com.sumativa1.mascotas.contracts;

import java.util.List;

public interface RepositoryContract<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
}