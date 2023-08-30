package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellOption;

public interface BookCommand {
    String insert(
            @ShellOption(value = "bn") String bookName,
            @ShellOption(value = "ai") Long authorId,
            @ShellOption(value = "an") String authorName,
            @ShellOption(value = "al") String lastname,
            @ShellOption(value = "gi") Long genreId,
            @ShellOption(value = "gn") String genreName
    );
    String getAllBook();
    String getBook(@ShellOption(value = "bi") Long id);
    String deleteBook(@ShellOption(value = "bi") Long id);
}
