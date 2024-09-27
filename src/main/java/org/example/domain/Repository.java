package org.example.domain;

import java.util.Optional;

public interface Repository<T> {

    long save(T dto);

    Optional<T> get(long id);

    void update(long id, T dto);

    void delete(long id);
}
