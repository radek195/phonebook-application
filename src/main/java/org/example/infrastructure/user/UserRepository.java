package org.example.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.Repository;
import org.example.domain.user.UserDto;
import org.example.infrastructure.Dao;

import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepository implements Repository<UserDto> {

    private final Dao<UserDto> userDao;

    @Override
    public long save(UserDto dto) {
        try {
            return userDao.save(dto);
        } catch (SQLException e) {
            throw new RuntimeException("Could not insert user: " + dto);
        }
    }

    @Override
    public Optional<UserDto> get(long id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new RuntimeException("Could not retrieve user using id: " + id);
        }
    }

    @Override
    public void update(long id, UserDto dto) {
        try {
            userDao.update(id, dto);
        } catch (SQLException e) {
            throw new RuntimeException("Could not update user with id: " + id);
        }
    }

    @Override
    public void delete(long id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete contact with id: " + id);
        }
    }
}
