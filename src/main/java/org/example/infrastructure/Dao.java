package org.example.infrastructure;

import java.sql.SQLException;

public interface Dao<T> {
    long save(T dto) throws SQLException;

    T get(long id) throws SQLException;

    void update(long id, T dto) throws SQLException;

    void delete(long id) throws SQLException;
}
