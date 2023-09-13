package ru.otus.spring.converter;

import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorConverter {
    String getAllAuthorsToString(List<Author> authorList);
    String getOneAuthorToString(Author author);
}
