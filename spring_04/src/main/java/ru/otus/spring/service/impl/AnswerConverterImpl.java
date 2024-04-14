package ru.otus.spring.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.service.AnswerConverter;

@Component
public class AnswerConverterImpl implements AnswerConverter {
    @Override
    public String convertAnswerToString(int answerNumber, String answer) {
        return answerNumber + " | " + answer;
    }
}
