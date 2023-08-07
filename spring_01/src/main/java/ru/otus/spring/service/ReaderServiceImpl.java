package ru.otus.spring.service;

import ru.otus.spring.models.Question;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private final List<Question> questions;

    public ReaderServiceImpl(QuestionsServiceImpl questionsService){
        questions = questionsService.getQuestions();
    }

    @Override
    public void print() {
        for (Question question: questions) {
            System.out.println(question.getQuestion());
        }
    }
}
