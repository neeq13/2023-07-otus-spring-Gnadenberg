package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repostories.GenreRepository;
import ru.otus.spring.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    @Override
    public Genre getGenreById(long uuid) {
        return genreRepository.getById(uuid);
    }
}
