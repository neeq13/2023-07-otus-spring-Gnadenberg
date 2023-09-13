package ru.otus.spring.repostories;

import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepository {
    void insert(Book book);
    Book getById(long uuid);
    List<Book> getByAuthorId(long uuid);
    List<Book> getByGenreId(long uuid);
    List<Book> getAll();
    void deleteById(long uuid);
    void deleteByAuthorId(long uuid);
    void deleteByGenreId(long uuid);
}
