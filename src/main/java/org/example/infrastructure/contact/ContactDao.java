package org.example.infrastructure.contact;

import lombok.RequiredArgsConstructor;
import org.example.domain.contact.ContactDto;
import org.example.infrastructure.Dao;
import org.example.infrastructure.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.example.infrastructure.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class ContactDao implements Dao<ContactDto> {

    private final DbConnection dbConnection;

    @Override
    public long save(ContactDto contactDto) throws SQLException {
        String query = String.format("INSERT INTO %s.CONTACTS(users_id, name, surname, email, phone_number, description) VALUES(?, ?, ?, ?, ?, ?)",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, contactDto.users_id());
        statement.setString(2, contactDto.name());
        statement.setString(3, contactDto.surname());
        statement.setString(4, contactDto.email());
        statement.setString(5, contactDto.phoneNumber());
        statement.setString(6, contactDto.description());

        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("Could not get id of saved record.");
    }

    @Override
    public Optional<ContactDto> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.CONTACTS WHERE ID = ?", SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();

        return Optional.ofNullable(rs.next() ? ContactDto.from(rs) : null);
    }

    @Override
    public void update(long id, ContactDto contactDto) throws SQLException {
        String query = String.format("UPDATE %s.CONTACTS SET name = ?, surname = ? , email = ?, phone_number = ?, description = ? WHERE ID = ?",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, contactDto.name());
        statement.setString(2, contactDto.surname());
        statement.setString(3, contactDto.email());
        statement.setString(4, contactDto.phoneNumber());
        statement.setString(5, contactDto.description());
        statement.setLong(6, id);

        statement.executeUpdate();
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.CONTACTS WHERE ID = ?",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        statement.executeUpdate();
    }
}
