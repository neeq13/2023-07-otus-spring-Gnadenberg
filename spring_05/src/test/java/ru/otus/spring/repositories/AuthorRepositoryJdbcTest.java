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
import ru.otus.spring.repostories.jdbc.AuthorRepositoryJdbc;
import ru.otus.spring.repostories.jdbc.BookRepositoryJdbc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование репозитория авторов")
@JdbcTest
@Import({AuthorRepositoryJdbc.class, BookRepositoryJdbc.class})
public class AuthorRepositoryJdbcTest {
    private Author expectedAuthor;
    private static final int EXISTING_AUTHOR_ID = 1;

    @Autowired
    private AuthorRepositoryJdbc repositoryJdbc;

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
        expectedAuthor = new Author(1, "Vitalii", "Zikov");
    }

    @DisplayName("Проверяем добавление в репозиторий записи")
    @Test
    void shouldInsertBook() {
        Author author = new Author(2, "Andrei", "Dai");
        repositoryJdbc.insert(author);
        Author actualAuthor = repositoryJdbc.getById(author.getUuid());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(author);
    }

    @DisplayName("Возвращает ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedBookById() {
        Author actualAuthor = repositoryJdbc.getById(expectedAuthor.getUuid());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> repositoryJdbc.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        repositoryJdbc.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> repositoryJdbc.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Возвращает ожидаемый список авторов")
    @Test
    void shouldReturnExpectedPersonsList() {
        List<Author> actualBookList = repositoryJdbc.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedAuthor);
    }
}
