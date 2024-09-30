package org.example.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.repository.UserRepository;
import org.example.infrastructure.user.IncorrectCredentialsException;

import java.util.Optional;

public interface UserService {

    Long validateUserCredentials(String username, String password) throws IncorrectCredentialsException;

    @RequiredArgsConstructor
    class Impl implements UserService {

        public final UserRepository userRepository;

        @Override
        public Long validateUserCredentials(String username, String password) throws IncorrectCredentialsException {
            Optional<Long> userId = userRepository.getIdForUsernameAndPassword(username, password);

            if (userId.isPresent()) {
                return userId.get();
            }
            throw new IncorrectCredentialsException();
        }


    }
}
