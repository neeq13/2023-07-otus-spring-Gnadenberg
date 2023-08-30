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
import ru.otus.spring.models.Genre;
import ru.otus.spring.repostories.jdbc.BookRepositoryJdbc;
import ru.otus.spring.repostories.jdbc.GenreRepositoryJdbc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование репозитория авторов")
@JdbcTest
@Import({GenreRepositoryJdbc.class, BookRepositoryJdbc.class})
public class GenreRepositoryJdbcTest {
    private Genre expectedGenre;
    private static final int EXISTING_ID = 1;

    @Autowired
    public GenreRepositoryJdbc repositoryJdbc;

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
        expectedGenre = new Genre(1, "Fantasy");
    }

    @DisplayName("Проверяем добавление в репозиторий записи")
    @Test
    void shouldInsertBook() {
        Genre genre = new Genre(2, "Alternative history");
        repositoryJdbc.insert(genre);
        Genre actualGenre = repositoryJdbc.getById(genre.getUuid());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(genre);
    }

    @DisplayName("Возвращает ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Genre actualGenre = repositoryJdbc.getById(expectedGenre.getUuid());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
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
        List<Genre> actualBookList = repositoryJdbc.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedGenre);
    }
}
