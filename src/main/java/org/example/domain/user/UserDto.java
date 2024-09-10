package org.example.domain.user;

public record UserDto(
   long id,
   String name,
   String surname,
   String email,
   String username,
   String password
) {}
