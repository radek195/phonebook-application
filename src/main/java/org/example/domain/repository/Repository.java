package org.example.domain.repository;

import org.example.infrastructure.user.ExistingUsernameException;

import java.util.Optional;

public interface Repository<T> {

    Long save(T dto) throws ExistingUsernameException;

    Optional<T> get(long id);

    Long update(long id, T dto);

    void delete(long id);

}
