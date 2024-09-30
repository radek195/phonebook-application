package org.example.domain.repository;

import org.example.domain.user.UserDto;

import java.util.Optional;

public interface UserRepository extends Repository<UserDto> {

    Optional<Long> getIdForUsernameAndPassword(String username, String password);
}
