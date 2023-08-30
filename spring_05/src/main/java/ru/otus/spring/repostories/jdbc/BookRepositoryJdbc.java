package ru.otus.spring.repostories.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repostories.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryJdbc implements BookRepository {
    private final NamedParameterJdbcOperations operations;

    public BookRepositoryJdbc(NamedParameterJdbcOperations operations) {
        this.operations = operations;
    }

    @Override
    public void insert(Book book) {
        Integer author = null;
        Integer genre = null;
        if (book.getAuthor().getUuid() == null) {
            author = operations.update("insert into authors (name, lastname) " +
                            "values (:name, :lastname)",
                    Map.of("name", book.getAuthor().getName(),
                            "lastname", book.getAuthor().getLastname()));
        }
        if (book.getGenre().getUuid() == null) {
            genre = operations.update("insert into genres (name) " +
                            "values (:name)",
                    Map.of("name", book.getGenre().getName()));
        }
        operations.update("insert into books (book_name, author_id, genre_id) " +
                        "values (:book_name, :author_id, :genre_id)",
                Map.of("book_name", book.getBookName(),
                        "author_id", author == null ? book.getAuthor().getUuid() : author,
                        "genre_id", genre == null ? book.getGenre().getUuid() : genre));
    }

    @Override
    public Book getById(long uuid) {
        return operations.queryForObject(
                "select bk.id, bk.book_name, au.id, au.name, au.lastname, gs.id, gs.name " +
                        "from books bk " +
                        "left join authors au " +
                        "on bk.author_id = au.id " +
                        "left join genres gs " +
                        "on bk.genre_id = gs.id " +
                        "where bk.id = :id ",
                Map.of("id", uuid), new BookMapper()
        );
    }

    @Override
    public List<Book> getByAuthorId(long uuid) {
        return operations.query(
                "select bk.id, bk.book_name, au.id, au.name, au.lastname, gs.id, gs.name " +
                        "from books bk " +
                        "left join authors au " +
                        "on bk.author_id = au.id " +
                        "left join genres gs " +
                        "on bk.genre_id = gs.id " +
                        "where bk.author_id = :id ",
                Map.of("id", uuid), new BookMapper()
        );
    }

    @Override
    public List<Book> getByGenreId(long uuid) {
        return operations.query(
                "select bk.id, bk.book_name, au.id, au.name, au.lastname, gs.id, gs.name " +
                        "from books bk " +
                        "left join authors au " +
                        "on bk.author_id = au.id " +
                        "left join genres gs " +
                        "on bk.genre_id = gs.id " +
                        "where bk.genre_id = :id ",
                Map.of("id", uuid), new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return operations.query(
                "select bk.id, bk.book_name, au.id, au.name, au.lastname, gs.id, gs.name " +
                        "from books bk " +
                        "left join authors au " +
                        "on bk.author_id = au.id " +
                        "left join genres gs " +
                        "on bk.genre_id = gs.id ",
                new BookMapper()
        );
    }

    @Override
    public void deleteById(long uuid) {
        operations.update(
                "delete from books where id = :id ",
                Map.of("id", uuid)
        );
    }

    @Override
    public void deleteByAuthorId(long uuid) {
        operations.update(
                "delete from books where author_id = :id ",
                Map.of("id", uuid)
        );
    }

    @Override
    public void deleteByGenreId(long uuid) {
        operations.update(
                "delete from books where genre_id = :genre_id ",
                Map.of("genre_id", uuid)
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getInt(1);
            String bookName = resultSet.getString(2);
            Author author = new Author(resultSet.getLong(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            Genre genre = new Genre(resultSet.getLong(6),
                    resultSet.getString(7));

            return new Book(id, bookName, author, genre);
        }
    }
}
