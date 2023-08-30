package ru.otus.spring.converter.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.converter.AuthorConverter;
import ru.otus.spring.models.Author;

import java.util.List;

@Component
public class AuthorConverterImp implements AuthorConverter {
    @Override
    public String getAllAuthorsToString(List<Author> authorList) {
        StringBuilder builder = new StringBuilder();

        for (Author author : authorList) {
            builder.append("---").append("\n");
            builder.append(author.getUuid()).append(" | ");
            builder.append(getOneAuthorToString(author));
            builder.append("---").append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getOneAuthorToString(Author author) {
        return "Автор: " +
                author.getFullName() +
                "\n";
    }
}
