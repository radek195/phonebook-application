package integration.dao

import common.DataHelper
import common.DbHelper
import org.example.domain.user.UserDto
import org.example.infrastructure.DbConnection
import org.example.infrastructure.user.UserDaoImpl
import spock.lang.Specification

class UserDaoImplIT extends Specification implements DataHelper {

    DbHelper dbHelper = new DbHelper()

    DbConnection dbConnection = new DbConnection()
    UserDaoImpl userDao = new UserDaoImpl(dbConnection)

    def setup() {
        dbHelper.cleanupTables()
    }

    def "should save user"() {
        given:
            UserDto user = getUserOne()
        when:
            def id = userDao.save(user)

        then:
            def actualUser = dbHelper.selectFromUsersById(id).first()
            actualUser.get("name") == user.name()
            actualUser.get("surname") == user.surname()
            actualUser.get("email") == user.email()
            actualUser.get("username") == user.username()
            actualUser.get("password") == user.password()
    }

    def "should retrieve saved user"() {
        given:
            UserDto user = getUserOne()
            dbHelper.insertUser(user)

        when:
            def retrievedUser = userDao.get(user.id()).get()

        then:
            retrievedUser.name() == user.name()
            retrievedUser.surname() == user.surname()
            retrievedUser.email() == user.email()
            retrievedUser.username() == user.username()
            retrievedUser.password() == user.password()
    }

    def "should update saved user"() {
        given:
            UserDto existingUser = getUserOne()
            UserDto updatedData = getUserTwo()

            dbHelper.insertUser(existingUser)

        when:
            userDao.update(existingUser.id(), updatedData)

        then:
            def actualUser = dbHelper.selectFromUsersById(existingUser.id()).first()
            actualUser.get("name") == updatedData.name()
            actualUser.get("surname") == updatedData.surname()
            actualUser.get("email") == updatedData.email()
            actualUser.get("username") == updatedData.username()
            actualUser.get("password") == updatedData.password()
    }

    def "should delete saved user"() {
        given:
            UserDto existingUser = getUserOne()

            dbHelper.insertUser(existingUser)

        when:
            userDao.delete(existingUser.id())

        then:
            def actualUser = dbHelper.selectFromUsersById(existingUser.id())
            actualUser.size() == 0
    }

    def "should return user id for username and password"() {
        given:
            UserDto user = getUserOne()
            dbHelper.insertUser(user)

        when:
            def retrievedUserId = userDao.getIdForUsernameAndPassword("perrym", "M4th1a5").get()

        then:
            retrievedUserId == 1
    }

}
