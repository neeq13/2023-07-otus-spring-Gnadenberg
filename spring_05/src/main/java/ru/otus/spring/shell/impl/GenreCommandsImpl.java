package ru.otus.spring.shell.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.converter.GenreConverter;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.shell.GenreCommand;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommandsImpl implements GenreCommand {
    private final GenreService genreService;
    private final GenreConverter genreConverter;

    @Override
    @ShellMethod(value = "Get all genres", key = {"ag", "all-genres"}, group = "Genres")
    public String getAllGenres() {
        return genreConverter.getAllGenresToString(genreService.getAll());
    }

    @Override
    @ShellMethod(value = "Get one genres", key = {"gg", "get-genres"}, group = "Genres")
    public String getGenre(@ShellOption(value = "gi") Long id) {
        return genreConverter.getOneGenreToString(genreService.getGenreById(id));
    }
}
