package org.example.domain.contact;

import lombok.RequiredArgsConstructor;
import org.example.domain.repository.ContactRepository;
import org.example.infrastructure.user.ExistingUsernameException;

import java.util.List;

public interface ContactService {

    List<ContactDto> getAllContactsForUser(Long loggedInUserId);

    Long updateContact(ContactDto contact, long id);

    Long saveContact(ContactDto contact);

    @RequiredArgsConstructor
    class Impl implements ContactService {

        public final ContactRepository contactRepository;

        @Override
        public List<ContactDto> getAllContactsForUser(Long loggedInUserId) {
            return contactRepository.getAllForUser(loggedInUserId);
        }

        @Override
        public Long updateContact(ContactDto contact, long id) {
            return contactRepository.update(id, contact);
        }

        @Override
        public Long saveContact(ContactDto contact) {
            try {
                return contactRepository.save(contact);
            } catch (ExistingUsernameException e) {
                return null;
            }
        }
    }
}
