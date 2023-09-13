package ru.otus.spring.shell.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.converter.AuthorConverter;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.shell.AuthorCommand;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommandsImpl implements AuthorCommand {
    private final AuthorService authorService;
    private final AuthorConverter authorConverter;

    @Override
    @ShellMethod(value = "Get all authors", key = {"aa", "all-authors"}, group = "Author")
    public String getAllAuthor() {
        return authorConverter.getAllAuthorsToString(authorService.getAll());
    }

    @Override
    @ShellMethod(value = "Get one author", key = {"ga", "get-authors"}, group = "Author")
    public String getAuthor(@ShellOption(value = "ai") Long id) {
        return authorConverter.getOneAuthorToString(authorService.getAuthorById(id));
    }
}
