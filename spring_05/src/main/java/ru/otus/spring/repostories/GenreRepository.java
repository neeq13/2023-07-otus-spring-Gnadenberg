package ru.otus.spring.repostories;

import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreRepository {
    void insert(Genre genre);
    Genre getById(long uuid);
    List<Genre> getAll();
    void deleteById(long uuid);
}
