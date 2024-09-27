package org.example.infrastructure.contact;

import lombok.RequiredArgsConstructor;
import org.example.domain.Repository;
import org.example.domain.contact.ContactDto;
import org.example.infrastructure.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ContactRepository implements Repository<ContactDto> {

    private final Dao<ContactDto> contactDao;

    @Override
    public long save(ContactDto dto) {
        try {
            return contactDao.save(dto);
        } catch (SQLException e) {
            throw new RuntimeException("Could not save contact: " + dto);
        }
    }

    @Override
    public Optional<ContactDto> get(long id) {
        try {
            return contactDao.get(id);
        } catch (SQLException e) {
            throw new RuntimeException("Could not retrieve contact using id: " + id);
        }
    }

    @Override
    public void update(long id, ContactDto dto) {
        try {
            contactDao.update(id, dto);
        } catch (SQLException e) {
            throw new RuntimeException("Could not update contact with id: " + id);
        }
    }

    @Override
    public void delete(long id) {
        try {
            contactDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete contact with id: " + id);
        }
    }

    @Override
    public List<ContactDto> getAllForUser(long id) {
        try {
            return contactDao.getAllForUser(id);
        } catch (SQLException e) {
            throw new RuntimeException("Could retrieve contacts");
        }
    }
}
