package org.example.domain.contact;

import lombok.Builder;

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
}
