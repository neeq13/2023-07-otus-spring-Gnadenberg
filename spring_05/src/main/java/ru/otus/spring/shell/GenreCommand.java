package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellOption;

public interface GenreCommand {
    String getAllGenres();
    String getGenre(@ShellOption(value = "gi") Long id);
}
