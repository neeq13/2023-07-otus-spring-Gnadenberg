package ru.otus.spring.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repostories.jdbc.AuthorRepositoryJdbc;
import ru.otus.spring.repostories.jdbc.BookRepositoryJdbc;
import ru.otus.spring.repostories.jdbc.GenreRepositoryJdbc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование репозитория книг")
@JdbcTest
@Import({BookRepositoryJdbc.class, AuthorRepositoryJdbc.class, GenreRepositoryJdbc.class})
public class BookRepositoryJdbcTest {
    private Author author;
    private Genre genre;
    private Book expectedBook;
    private static final int EXISTING_ID = 1;

    @Autowired
    public BookRepositoryJdbc repositoryJdbc;

    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @BeforeEach
    void setUp() {
        genre = new Genre(1, "Fantasy");
        author = new Author(1, "Vitalii", "Zikov");
        expectedBook = new Book(1, "Nameless slave", author, genre);
    }

    @DisplayName("Проверяем добавление в репозиторий записи")
    @Test
    void shouldInsertBook() {
        Genre genre = new Genre(2, "Alternative history");
        Author author = new Author(2, "Andrei", "Dai");
        Book book = new Book(2, "Guide", author, genre);
        repositoryJdbc.insert(book);
        Book actualBook = repositoryJdbc.getById(book.getUuid());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(book);
    }

    @DisplayName("Возвращает ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book actualBook = repositoryJdbc.getById(expectedBook.getUuid());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Удалять заданною книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> repositoryJdbc.getById(EXISTING_ID))
                .doesNotThrowAnyException();

        repositoryJdbc.deleteById(EXISTING_ID);

        assertThatThrownBy(() -> repositoryJdbc.getById(EXISTING_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Возвращает ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookList() {
        List<Book> actualBookList = repositoryJdbc.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("Возвращает ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookListByAuthorId() {
        List<Book> actualBookList = repositoryJdbc.getByAuthorId(author.getUuid());
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("Возвращает ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookListByGenreId() {
        List<Book> actualBookList = repositoryJdbc.getByGenreId(genre.getUuid());
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }
}
