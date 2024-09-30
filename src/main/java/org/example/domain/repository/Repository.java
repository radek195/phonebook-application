package org.example.domain.repository;

import java.util.Optional;

public interface Repository<T> {

    long save(T dto);

    Optional<T> get(long id);

    Long update(long id, T dto);

    void delete(long id);

}
