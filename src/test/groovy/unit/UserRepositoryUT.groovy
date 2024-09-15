package unit

import common.DataHelper
import org.example.domain.Repository
import org.example.infrastructure.Dao
import org.example.infrastructure.contact.ContactRepository
import org.example.infrastructure.user.UserRepository
import spock.lang.Specification
import spock.lang.Subject

import java.sql.SQLException

class UserRepositoryUT extends Specification implements DataHelper {

    Dao userDao = Stub()

    @Subject
    Repository userRepository = new UserRepository(userDao)

    def "Should return correct id when saving contact"() {
        given:
            def user = getUserOne()
            long expectedId = 16
            userDao.save(user) >> expectedId

        expect:
            userRepository.save(user) == 16

    }

    def "Should throw runtime exception when inserting contact failed"() {
        given:
            def user = getUserOne()
            userDao.save(user) >> new SQLException()

        when:
            userRepository.save(user)

        then:
            thrown(RuntimeException)
    }

    def "Should return correct contact when getting contact"() {
        given:
            def user = getUserOne()
            userDao.get(162) >> user

        expect:
            def retrievedUser = userRepository.get(162)
            retrievedUser == user
            retrievedUser.id() == user.id()
            retrievedUser.name() == user.name()
            retrievedUser.surname() == user.surname()
            retrievedUser.email() == user.email()
            retrievedUser.username() == user.username()
            retrievedUser.password() == user.password()
    }

    def  "Should throw runtime exception when updating contact failed"() {
        given:
            def user = getUserOne()
            userDao.update(user.id(), user) >> new SQLException()

        when:
            userRepository.update(user.id(), user)

        then:
            thrown(RuntimeException)
    }

    def  "Should throw runtime exception when deleding contact failed"() {
        given:
            userDao.delete(18) >> new SQLException()

        when:
            userRepository.delete(18)

        then:
            thrown(RuntimeException)
    }
}
