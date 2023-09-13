package ru.otus.spring.converter.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.converter.GenreConverter;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Genre;

import java.util.List;

@Component
public class GenreConverterImpl implements GenreConverter {
    @Override
    public String getAllGenresToString(List<Genre> genreList) {
        StringBuilder builder = new StringBuilder();

        for (Genre genre : genreList) {
            builder.append("---").append("\n");
            builder.append(genre.getUuid()).append(" | ");
            builder.append(getOneGenreToString(genre));
            builder.append("---").append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getOneGenreToString(Genre genre) {
        return "Жанр: " +
                genre.getName() +
                "\n";
    }
}
