package com.viamatica.viamatica.business.port;

import java.util.List;

public interface IEntityCrudService<T, ID> {
    List<T> getAll();

    T getById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
