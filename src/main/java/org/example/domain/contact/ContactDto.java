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

    public static ContactDto userIdDecorator(ContactDto contactDto, long id)  {
        return ContactDto.builder()
                .users_id(id)
                .name(contactDto.name)
                .surname(contactDto.surname)
                .email(contactDto.email)
                .phoneNumber(contactDto.phoneNumber)
                .description(contactDto.description)
                .build();
    }

    public void displayContact() {
        System.out.printf(
                "|%-10s |%-10s |%-10s |%-10s |%-10s |%-24s %n",
                this.id, this.name, this.surname, this.phoneNumber, this.description, this.email
                );
    }
}
