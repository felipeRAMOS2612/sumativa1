package com.sumativa1.envios.domain.contracts;

import java.util.List;

public interface RepositoryContract<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    T update(Long id, T entity);
}
