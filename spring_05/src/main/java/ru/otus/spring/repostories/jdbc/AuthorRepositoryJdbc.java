package ru.otus.spring.repostories.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Author;
import ru.otus.spring.repostories.AuthorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryJdbc implements AuthorRepository {
    private final NamedParameterJdbcOperations operations;
    private final BookRepositoryJdbc bookRepositoryJdbc;

    public AuthorRepositoryJdbc(NamedParameterJdbcOperations operations,
                                BookRepositoryJdbc bookRepositoryJdbc) {
        this.operations = operations;
        this.bookRepositoryJdbc = bookRepositoryJdbc;
    }

    @Override
    public void insert(Author author) {
        operations.update("insert into authors (id, name, lastname) " +
                        "values (:id, :name, :lastname)",
                Map.of("id", author.getUuid(),
                        "name", author.getName(),
                        "lastname", author.getLastname()));
    }

    @Override
    public Author getById(long uuid) {
        return operations.queryForObject(
                "select id, name, lastname from authors " +
                        "where id = :id ",
                Map.of("id", uuid), new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return operations.query(
                "select id, name, lastname from authors",
                new AuthorMapper()
        );
    }

    @Override
    public void deleteById(long uuid) {
        bookRepositoryJdbc.deleteByAuthorId(uuid);
        operations.update(
                "delete from authors where id = :id",
                Map.of("id", uuid)
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");

            return new Author(id, name, lastname);
        }
    }
}
