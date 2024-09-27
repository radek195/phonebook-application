package org.example.domain.user;

import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public record UserDto(
        long id,
        String name,
        String surname,
        String email,
        String username,
        String password
) {

    public static UserDto from(ResultSet resultSet) throws SQLException {
        return UserDto.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();
    }
}
