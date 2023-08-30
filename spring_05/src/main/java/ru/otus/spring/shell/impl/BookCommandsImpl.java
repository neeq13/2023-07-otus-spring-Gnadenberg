package ru.otus.spring.shell.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.converter.BookConverter;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.shell.BookCommand;

@ShellComponent
@RequiredArgsConstructor
public class BookCommandsImpl extends AbstractShellComponent implements BookCommand {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookConverter bookConverter;

    @Override
    @ShellMethod(value = "Insert new book", key = {"ib", "insert-book"}, group = "Book")
    public String insert(
            @ShellOption(value = "bn") String bookName,
            @ShellOption(value = "ai") Long authorId,
            @ShellOption(value = "an") String authorName,
            @ShellOption(value = "al") String lastname,
            @ShellOption(value = "gi") Long genreId,
            @ShellOption(value = "gn") String genreName
    ) {
        Book book = new Book();
        Author author = new Author();
        Genre genre = new Genre();

        if (authorId == -1 & (authorName.equals("null") & lastname.equals("null"))) {
            authorService.getAll();
            return "Заполните автора книги или выберите из списка";
        } else if (authorId == -1 & (!authorName.equals("null") & !lastname.equals("null"))) {
            author.setName(authorName);
            author.setLastname(lastname);
        }
        if (authorId != -1) {
            author = authorService.getAuthorById(authorId);
            if (author == null) {
                return "Автора с данным айди не существует";
            }
        }

        if (genreId == -1 & genreName.equals("null")) {
            genreService.getAll();
            return "Заполните жанр книги или выберите из списка";
        } else if (genreId == -1 & !genreName.equals("null")) {
            genre.setName(genreName);
        }
        if (genreId != -1) {
            genre = genreService.getGenreById(genreId);
            if (genre == null) {
                return "Жанра с данным айди не существует";
            }
        }

        if (!bookName.equals("null")) {
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setGenre(genre);
            bookService.insert(book);
        } else {
            return "Заполните название книги";
        }

        return "Книга успешно записана";
    }

    @Override
    @ShellMethod(value = "Get all books", key = {"ab", "all-book"}, group = "Book")
    public String getAllBook() {
        return bookConverter.getAllBookToString(bookService.getAll());
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"gb", "get-book"}, group = "Book")
    public String getBook(@ShellOption(value = "bi") Long id) {
        return bookConverter.getOneBookToString(bookService.getBookById(id));
    }

    @Override
    @ShellMethod(value = "Delete book by id", key = {"db", "delete-book"}, group = "Book")
    public String deleteBook(@ShellOption(value = "bi") Long id) {
        bookService.deleteBookById(id);
        return "Книга удалена";
    }

}
