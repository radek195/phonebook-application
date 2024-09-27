package org.example.domain.user;

import java.util.Optional;

public interface UserService {

    Optional<Long> validateUserCredentials(String username, String password);

}
