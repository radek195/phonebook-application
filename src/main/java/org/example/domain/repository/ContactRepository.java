package org.example.domain.repository;

import org.example.domain.contact.ContactDto;

import java.util.List;

public interface ContactRepository extends Repository<ContactDto> {

    List<ContactDto> getAllForUser(long id);
}
