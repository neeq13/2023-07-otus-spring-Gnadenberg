package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Question;
import ru.otus.spring.service.QuestionsService;
import ru.otus.spring.service.ReadToFileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    private final int QUESTION = 0;
    private final int FIRST_ANSWER = 1;
    private final int LAST_ANSWER = 5;
    private final int CORRECT_ANSWER = 5;
    private final List<String> textQuestions;

    @Autowired
    public QuestionsServiceImpl(ReadToFileService readToFileServiceImpl) {
        this.textQuestions = readToFileServiceImpl.getText();
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        for (String text: textQuestions) {
            Question question = new Question();
            String[] textSplit = text.split(",");
            String[] answer = Arrays.copyOfRange(textSplit, FIRST_ANSWER, LAST_ANSWER);

            question.setQuestion(textSplit[QUESTION]);
            question.getAnswers().addAll(Arrays.stream(answer).toList());
            question.setCorrectAnswer(Integer.parseInt(textSplit[CORRECT_ANSWER]));

            questionList.add(question);
        }
        return questionList;
    }
}
