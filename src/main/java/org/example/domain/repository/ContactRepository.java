package org.example.domain.repository;

import org.example.domain.contact.ContactDto;

import java.sql.SQLException;
import java.util.List;

public interface ContactRepository extends Repository<ContactDto> {

    List<ContactDto> getAllForUser(long id) throws SQLException;
}
