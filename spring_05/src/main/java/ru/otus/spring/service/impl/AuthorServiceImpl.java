package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Author;
import ru.otus.spring.repostories.AuthorRepository;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public Author getAuthorById(long uuid) {
        return authorRepository.getById(uuid);
    }
}
