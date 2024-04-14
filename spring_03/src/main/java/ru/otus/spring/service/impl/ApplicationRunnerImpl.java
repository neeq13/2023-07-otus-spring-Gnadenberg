package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.exceptions.IndexOutOfBoundsException;
import ru.otus.spring.models.Question;
import ru.otus.spring.models.Student;
import ru.otus.spring.models.StudentTest;
import ru.otus.spring.service.AnswerConverter;
import ru.otus.spring.service.ApplicationRunner;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.QuestionsService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final IOService ioService;
    private final StudentDao studentDaoSimple;
    private final QuestionsService questionsService;
    private final AnswerConverter answerConverter;
    private final AppConfig appConfig;
    private final MessageSource messageSource;

    @Override
    public void run() {
        Student student = introduce();
        ioService.outputString(messageSource.getMessage("test.start", new String[]{student.getFullName()}, appConfig.getLocale()));
        boolean result = startTest();
        StudentTest test = new StudentTest(student, result, appConfig, messageSource);
        ioService.outputString(test.toString());
    }

    private Student introduce() {
        ioService.outputString(messageSource.getMessage("test.student.name", new String[]{}, appConfig.getLocale()));
        String name = readString();
        ioService.outputString(messageSource.getMessage("test.student.lastname", new String[]{}, appConfig.getLocale()));
        String lastname = readString();
        return studentDaoSimple.getStudent(name, lastname);
    }

    private boolean startTest() {
        List<Question> questionList = questionsService.getQuestions();
        List<Integer> result = new ArrayList<>();
         for (Question question: questionList) {
            try {
                ioService.outputString(question.getQuestion());
                getAnswer(question.getAnswers());
                int choice = readSelectedOptionNumber();
                int correctAnswer = question.getCorrectAnswer();
                result.add(choice == correctAnswer ? 1 : 0);
            } catch (NumberFormatException e) {
                ioService.outputString(messageSource.getMessage("test.error.number", new String[]{}, appConfig.getLocale()));
            } catch (IndexOutOfBoundsException e) {
                ioService.outputString(messageSource.getMessage("test.error.invalid", new String[]{}, appConfig.getLocale()));
            }
        }
         return result.stream()
                 .filter(integer -> integer > 0)
                 .count() > 3;
    }

    private void getAnswer(List<String> answers) {
        IntStream.range(1, answers.size() + 1)
                .mapToObj(k -> answerConverter.convertAnswerToString(k, answers.get(k - 1)))
                .forEach(ioService::outputString);
        ioService.outputString("");
    }

    private int readSelectedOptionNumber() {
        return ioService.readInt();
    }

    private String readString() {
        return ioService.readString();
    }
}
