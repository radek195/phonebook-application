package org.example.domain;

public interface Repository<T> {

    long save(T dto);

    T get(long id);

    void update(long id, T dto);

    void delete(long id);
}
