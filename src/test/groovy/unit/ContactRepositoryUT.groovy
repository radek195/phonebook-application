package unit

import common.DataHelper
import org.example.domain.Repository
import org.example.domain.contact.ContactDto
import org.example.infrastructure.Dao
import org.example.infrastructure.contact.ContactRepository
import spock.lang.Specification
import spock.lang.Subject

import java.sql.SQLException

class ContactRepositoryUT extends Specification implements DataHelper {

    Dao contactDao = Stub()

    @Subject
    Repository contactRepository = new ContactRepository(contactDao)

    def "Should return correct id when saving contact"() {
        given:
            def contact = getContactOneFor(1)
            long expectedId = 16
            contactDao.save(contact) >> expectedId

        expect:
            contactRepository.save(contact) == 16

    }

    def "Should throw runtime exception when inserting contact failed"() {
        given:
            def contact = getContactOneFor(1)
            contactDao.save(contact) >> new SQLException()

        when:
            contactRepository.save(contact)

        then:
            thrown(RuntimeException)
    }

    def "Should return correct contact when getting contact"() {
        given:
            def contact = getContactOneFor(1)
            contactDao.get(1) >> Optional.of(contact)

        expect:
            def retrievedContact = contactRepository.get(1).get()
            retrievedContact.users_id() == contact.users_id()
            retrievedContact.name() == contact.name()
            retrievedContact.surname() == contact.surname()
            retrievedContact.email() == contact.email()
            retrievedContact.phoneNumber() == contact.phoneNumber()
            retrievedContact.description() == contact.description()
    }

    def "Should throw runtime exception when updating contact failed"() {
        given:
            def contact = getContactOneFor(1)
            contactDao.update(contact.id(), contact) >> new SQLException()

        when:
            contactRepository.update(contact.id(), contact)

        then:
            thrown(RuntimeException)
    }

    def "Should throw runtime exception when deleding contact failed"() {
        given:
            contactDao.delete(18) >> new SQLException()

        when:
            contactRepository.delete(18)

        then:
            thrown(RuntimeException)
    }

    def "Should return list of contacts"() {
        given:
            def list = List.of(getContactOneFor(1), getContactTwoFor(1))
            contactDao.getAllForUser(1) >> list

        when:
            List<ContactDto> contactList = contactRepository.getAllForUser(1)

        then:
            contactList.size() == list.size()

    }
}
