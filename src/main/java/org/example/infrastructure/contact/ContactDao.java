package org.example.infrastructure.contact;

import org.example.domain.contact.ContactDto;
import org.example.infrastructure.Dao;

import java.sql.SQLException;
import java.util.List;

public interface ContactDao extends Dao<ContactDto> {
    List<ContactDto> getAllForUser(long userId) throws SQLException;
}
