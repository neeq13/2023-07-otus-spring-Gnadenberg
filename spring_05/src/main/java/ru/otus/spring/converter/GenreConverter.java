package ru.otus.spring.converter;

import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreConverter {
    String getAllGenresToString(List<Genre> genreList);
    String getOneGenreToString(Genre genre);
}
