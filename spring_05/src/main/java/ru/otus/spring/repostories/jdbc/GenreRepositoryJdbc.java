package ru.otus.spring.repostories.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repostories.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreRepositoryJdbc implements GenreRepository {
    private final NamedParameterJdbcOperations operations;
    private final BookRepositoryJdbc bookRepositoryJdbc;

    public GenreRepositoryJdbc(NamedParameterJdbcOperations operations,
                               BookRepositoryJdbc bookRepositoryJdbc) {
        this.operations = operations;
        this.bookRepositoryJdbc = bookRepositoryJdbc;
    }
    @Override
    public void insert(Genre genre) {
        operations.update("insert into genres (id, name) " +
                        "values (:id, :name)",
                Map.of("id", genre.getUuid(),
                        "name", genre.getName()));
    }

    @Override
    public Genre getById(long uuid) {
        return operations.queryForObject(
                "select id, name from genres " +
                        "where id = :id ",
                Map.of("id", uuid), new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return operations.query(
                "select id, name from genres", new GenreMapper()
        );
    }

    @Override
    public void deleteById(long uuid) {
        bookRepositoryJdbc.deleteByGenreId(uuid);
        operations.update(
                "delete from genres where id = :id ",
                Map.of("id", uuid)
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            return new Genre(id, name);
        }
    }
}
