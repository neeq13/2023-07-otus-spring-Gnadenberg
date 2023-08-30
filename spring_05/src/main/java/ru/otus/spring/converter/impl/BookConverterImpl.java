package ru.otus.spring.converter.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.converter.BookConverter;
import ru.otus.spring.models.Book;

import java.util.List;

@Component
public class BookConverterImpl implements BookConverter {

    @Override
    public String getAllBookToString(List<Book> bookList) {
        StringBuilder builder = new StringBuilder();

        for (Book book : bookList) {
            builder.append("---").append("\n");
            builder.append("Книга ");
            builder.append(book.getUuid()).append("\n");
            builder.append(getOneBookToString(book));
            builder.append("---").append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getOneBookToString(Book book) {
        return "Название книги: " +
                book.getBookName() +
                "\n" +
                "Автор: " +
                book.getAuthor().getFullName() +
                "\n" +
                "Жанр: " +
                book.getGenre().getName() +
                "\n";
    }
}
