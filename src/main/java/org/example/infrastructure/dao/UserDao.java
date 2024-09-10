package org.example.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.example.infrastructure.dao.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class UserDao {

    private final DbConnection dbConnection;

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

    public ResultSet get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.USERS WHERE ID = ?", SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet : null;
    }

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

    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.USERS WHERE ID = ?",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        statement.executeUpdate();
    }
}
