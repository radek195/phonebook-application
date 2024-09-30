package org.example.infrastructure.initializers;

import org.example.domain.repository.ContactRepository;
import org.example.domain.repository.UserRepository;
import org.example.infrastructure.DbConnection;
import org.example.infrastructure.contact.ContactDao;
import org.example.infrastructure.contact.ContactDaoImpl;
import org.example.infrastructure.contact.ContactRepositoryImpl;
import org.example.infrastructure.user.UserDao;
import org.example.infrastructure.user.UserDaoImpl;
import org.example.infrastructure.user.UserRepositoryImpl;

public class RepositoryProvider {

    private static final DbConnection connection = new DbConnection();


    public static ContactRepository getContactRepository() {
        ContactDao contactDao = new ContactDaoImpl(connection);
        return new ContactRepositoryImpl(contactDao);
    }

    public static UserRepository getUserRepository() {
        UserDao userDao = new UserDaoImpl(connection);
        return new UserRepositoryImpl(userDao);
    }

}
