package com.viamatica.viamatica.domain.repository;

import java.util.List;
import java.util.Optional;

public interface IEntityCrudRepository<T, ID> {
    List<T> getAll();

    Optional<T> getById(ID id);

    T create(T entity);

    T update(T entity);

    void delete(ID id);
}
