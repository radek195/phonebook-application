package org.example.domain.contact;

import java.util.List;

public interface ContactService {

    List<ContactDto> getAllContactsForUser(Long loggedInUserId);

    Long updateContact(ContactDto contact, long id);

    Long saveContact(ContactDto contact);
}
