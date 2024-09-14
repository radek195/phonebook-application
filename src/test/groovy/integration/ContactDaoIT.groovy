package integration

import common.DataHelper
import common.DbHelper
import org.example.domain.contact.ContactDto
import org.example.domain.user.UserDto
import org.example.infrastructure.contact.ContactDao
import org.example.infrastructure.DbConnection
import spock.lang.Specification

class ContactDaoIT extends Specification implements DataHelper {

    DbHelper dbHelper = new DbHelper()

    DbConnection dbConnection = new DbConnection()
    ContactDao contactDao = new ContactDao(dbConnection)

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
            def retrievedContact = contactDao.get(contact.id())

        then:
            retrievedContact.getString("users_id") == contact.users_id() as String
            retrievedContact.getString("name") == contact.name()
            retrievedContact.getString("surname") == contact.surname()
            retrievedContact.getString("email") == contact.email()
            retrievedContact.getString("phone_number") == contact.phoneNumber()
            retrievedContact.getString("description") == contact.description()
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

    def "should delete saved user"() {
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
}
