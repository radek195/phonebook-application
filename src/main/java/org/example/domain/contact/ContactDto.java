package org.example.domain.contact;

import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public record ContactDto(
        long id,
        long users_id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String description
) {
    public static ContactDto from(ResultSet resultSet) throws SQLException {
        return ContactDto.builder()
                .id(resultSet.getLong("id"))
                .users_id(resultSet.getLong("users_id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .phoneNumber(resultSet.getString("phone_number"))
                .description(resultSet.getString("description"))
                .build();
    }
}
