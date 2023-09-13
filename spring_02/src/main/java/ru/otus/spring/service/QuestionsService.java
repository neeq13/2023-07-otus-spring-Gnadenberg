package ru.otus.spring.service;

import ru.otus.spring.models.Question;

import java.util.List;

public interface QuestionsService {
    List<Question> getQuestions();
}
