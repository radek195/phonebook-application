package integration.dao

import common.DataHelper
import common.DbHelper
import org.example.domain.contact.ContactDto
import org.example.domain.user.UserDto
import org.example.infrastructure.contact.ContactDaoImpl
import org.example.infrastructure.DbConnection
import spock.lang.Specification

class ContactDaoImplIT extends Specification implements DataHelper {

    DbHelper dbHelper = new DbHelper()

    DbConnection dbConnection = new DbConnection()
    ContactDaoImpl contactDao = new ContactDaoImpl(dbConnection)

    def setup() {
        dbHelper.cleanupTables()
    }

    def "should save contact"() {
        given:
            UserDto user = getUserOne()
            dbHelper.insertUser(user)

            ContactDto contact = getContactOneFor(user.id())

        when:
            def id = contactDao.save(contact)

        then:
            def actualContact = dbHelper.selectFromContactsById(id).first()
            actualContact.get("users_id") == user.id()
            actualContact.get("name") == contact.name()
            actualContact.get("surname") == contact.surname()
            actualContact.get("email") == contact.email()
            actualContact.get("phone_number") == contact.phoneNumber()
            actualContact.get("description") == contact.description()
    }

    def "should retrieve saved contact"() {
        given:
            UserDto user = getUserOne()
            ContactDto contact = getContactOneFor(user.id())

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact)

        when:
            def retrievedContact = contactDao.get(contact.id()).get()

        then:
            retrievedContact.users_id() == contact.users_id()
            retrievedContact.name() == contact.name()
            retrievedContact.surname() == contact.surname()
            retrievedContact.email() == contact.email()
            retrievedContact.phoneNumber() == contact.phoneNumber()
            retrievedContact.description() == contact.description()
    }

    def "should update saved contact"() {
        given:
            UserDto user = getUserOne()
            ContactDto contact = getContactOneFor(user.id())
            ContactDto contactUpdated = getContactTwoFor(user.id())

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact)

        when:
            contactDao.update(contact.id(), contactUpdated)

        then:
            def retrievedContact = dbHelper.selectFromContactsById(contact.id()).first()
            retrievedContact.get("users_id") == contact.users_id() as Integer
            retrievedContact.get("name") == contactUpdated.name()
            retrievedContact.get("surname") == contactUpdated.surname()
            retrievedContact.get("email") == contactUpdated.email()
            retrievedContact.get("phone_number") == contactUpdated.phoneNumber()
            retrievedContact.get("description") == contactUpdated.description()
    }

    def "should delete saved contact"() {
        given:
            UserDto user = getUserOne()
            ContactDto contact = getContactOneFor(user.id())

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact)

        when:
            contactDao.delete(contact.id())

        then:
            def actualContact = dbHelper.selectFromContactsById(contact.id())
            actualContact.size() == 0

    }

    def "should retrieve saved contacts"() {
        given:
            UserDto user = getUserOne()
            ContactDto contact = getContactOneFor(user.id())
            ContactDto contact2 = getContactTwoFor(user.id())

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact)
            dbHelper.insertContact(contact2)

        when:
            def retrievedContacts = contactDao.getAllForUser(user.id())

        then:
            retrievedContacts.size() == 2
    }
}
