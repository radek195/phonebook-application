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

            def userId = user.id()
            ContactDto contact = getContactOne()

        when:
            def id = contactDao.save(contact, userId)

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
            def userId = user.id()
            ContactDto contact = getContactOne()

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact, userId)

        when:
            def retrievedContact = contactDao.get(contact.id())

        then:
            retrievedContact.getString("users_id") == userId as String
            retrievedContact.getString("name") == contact.name()
            retrievedContact.getString("surname") == contact.surname()
            retrievedContact.getString("email") == contact.email()
            retrievedContact.getString("phone_number") == contact.phoneNumber()
            retrievedContact.getString("description") == contact.description()
    }

    def "should update saved contact"() {
        given:
            UserDto user = getUserOne()
            def userId = user.id()
            ContactDto contact = getContactOne()
            ContactDto contactUpdated = getContactTwo()

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact, userId)

        when:
            contactDao.update(contact.id(), contactUpdated)

        then:
            def retrievedContact = dbHelper.selectFromContactsById(contact.id()).first()
            retrievedContact.get("users_id") == userId as Integer
            retrievedContact.get("name") == contactUpdated.name()
            retrievedContact.get("surname") == contactUpdated.surname()
            retrievedContact.get("email") == contactUpdated.email()
            retrievedContact.get("phone_number") == contactUpdated.phoneNumber()
            retrievedContact.get("description") == contactUpdated.description()
    }

    def "should delete saved user"() {
        given:
            UserDto user = getUserOne()
            def userId = user.id()
            ContactDto contact = getContactOne()

            dbHelper.insertUser(user)
            dbHelper.insertContact(contact, userId)

        when:
            contactDao.delete(contact.id())

        then:
            def actualContact = dbHelper.selectFromContactsById(contact.id())
            actualContact.size() == 0

    }
}
