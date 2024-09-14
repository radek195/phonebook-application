package org.example.domain.user;

import lombok.Builder;

@Builder
public record UserDto(
        long id,
        String name,
        String surname,
        String email,
        String username,
        String password
) {
}
