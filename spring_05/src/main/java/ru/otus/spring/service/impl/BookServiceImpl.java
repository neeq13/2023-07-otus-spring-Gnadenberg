package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.converter.BookConverter;
import ru.otus.spring.models.Book;
import ru.otus.spring.repostories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements ru.otus.spring.service.BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void insert(Book book) {
        bookRepository.insert(book);
    }

    @Override
    public Book getBookById(long uuid) {
        return bookRepository.getById(uuid);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public void deleteBookById(long uuid) {
        bookRepository.deleteById(uuid);
    }

}
