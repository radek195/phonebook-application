package org.example.infrastructure.user;

import org.example.domain.user.UserDto;
import org.example.infrastructure.Dao;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends Dao<UserDto> {

    Optional<Long> getIdForUsernameAndPassword(String username, String password) throws SQLException;
}
