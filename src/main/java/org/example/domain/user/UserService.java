package org.example.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.repository.UserRepository;
import org.example.infrastructure.user.ExistingUsernameException;
import org.example.infrastructure.user.IncorrectCredentialsException;

import java.util.Optional;

public interface UserService {

    Long validateUserCredentials(String username, String password) throws IncorrectCredentialsException;

    boolean registerNewUser(UserDto userDto);

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

        @Override
        public boolean registerNewUser(UserDto userDto) {
            try {
                return userRepository.save(userDto) != null;
            } catch (ExistingUsernameException e) {
                return false;
            }
        }


    }
}
