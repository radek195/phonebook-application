package common

import groovy.sql.Sql
import org.example.domain.user.UserDto

class DbHelper {
    Sql sql
    String schema = "phonebook"

    DbHelper() {
        sql = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password"
        )
    }

    def cleanupTables() {
        sql.execute("DELETE FROM ${schema}.CONTACTS" as String)
        sql.execute("DELETE FROM ${schema}.USERS" as String)
    }

    def selectFromUsersById(long id) {
        sql.rows("SELECT * FROM ${schema}.USERS WHERE ID = ${id}" as String)
    }

    void insertUser(UserDto user) {
        sql.execute(
                String.format("INSERT INTO %s.USERS(id, name, surname, email, username, password) VALUES(%s, '%s', '%s', '%s', '%s', '%s')",
                        schema,
                        user.id(),
                        user.name(),
                        user.surname(),
                        user.email(),
                        user.username(),
                        user.password()
                )
        )
    }
}
