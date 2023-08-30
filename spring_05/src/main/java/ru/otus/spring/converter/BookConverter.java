package ru.otus.spring.converter;

import ru.otus.spring.models.Book;

import java.util.List;

public interface BookConverter {
    String getAllBookToString(List<Book> bookList);
    String getOneBookToString(Book book);
}
