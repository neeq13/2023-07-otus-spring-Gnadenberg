package ru.otus.spring.repostories;

import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorRepository {
    void insert(Author author);
    Author getById(long uuid);
    List<Author> getAll();
    void deleteById(long uuid);
}
