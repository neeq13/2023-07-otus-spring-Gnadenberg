package ru.otus.spring.service;

import ru.otus.spring.utils.Reader;

import java.util.List;

public class ReaderServiceImpl {
    private final List<String> questions;

    public ReaderServiceImpl(Reader reader){
        questions = reader.getQuestions();
    }

    public void print() {
        for (String question: questions) {
            System.out.println(question);
        }
    }
}
