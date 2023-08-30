package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellOption;

public interface AuthorCommand {
    String getAllAuthor();
    String getAuthor(@ShellOption(value = "ai") Long id);
}
