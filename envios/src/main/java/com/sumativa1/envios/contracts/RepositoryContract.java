package com.sumativa1.envios.contracts;

import java.util.List;

public interface RepositoryContract<T> {
    List<T> findAll();
    T findById(Long id);
}
