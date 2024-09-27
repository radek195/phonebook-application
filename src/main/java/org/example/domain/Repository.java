package org.example.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    long save(T dto);

    Optional<T> get(long id);

    void update(long id, T dto);

    void delete(long id);

    List<T> getAllForUser(long id) throws SQLException;
}
