package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getGenreById(long uuid);
}
