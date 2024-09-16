package org.example.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.UserDto;
import org.example.infrastructure.Dao;
import org.example.infrastructure.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.infrastructure.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class UserDao implements Dao<UserDto> {

    private final DbConnection dbConnection;

    @Override
    public long save(UserDto userDto) throws SQLException {
        String query = String.format("INSERT INTO %s.USERS(name, surname, email, username, password) VALUES(?, ?, ?, ?, ?)",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, userDto.name());
        statement.setString(2, userDto.surname());
        statement.setString(3, userDto.email());
        statement.setString(4, userDto.username());
        statement.setString(5, userDto.password());

        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("Could not get id of saved record.");
    }

    @Override
    public UserDto get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.USERS WHERE ID = ?", SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return UserDto.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .build();
        }
        return null;
    }

    @Override
    public void update(long id, UserDto userDto) throws SQLException {
        String query = String.format("UPDATE %s.USERS SET name = ?, surname = ? , email = ?, username = ?, password = ? WHERE ID = ?",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, userDto.name());
        statement.setString(2, userDto.surname());
        statement.setString(3, userDto.email());
        statement.setString(4, userDto.username());
        statement.setString(5, userDto.password());
        statement.setLong(6, id);

        statement.executeUpdate();
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.USERS WHERE ID = ?",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        statement.executeUpdate();
    }
}
