package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author getAuthorById(long uuid);
}
