package ru.otus.spring.service;

import ru.otus.spring.models.Book;

import java.util.List;

public interface BookService {
    void insert(Book book);
    Book getBookById(long uuid);
    List<Book> getAll();
    void deleteBookById(long uuid);
}
