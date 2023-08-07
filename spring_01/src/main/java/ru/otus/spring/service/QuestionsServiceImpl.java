package ru.otus.spring.service;

import ru.otus.spring.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceImpl implements QuestionsService{
    private final List<String> textQuestions;

    public QuestionsServiceImpl(IOService ioService) {
        this.textQuestions = ioService.getText();
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        for (String text: textQuestions) {
            Question question = new Question();
            question.setQuestion(text);
            questionList.add(question);
        }
        return questionList;
    }
}
