package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.exceptions.IndexOutOfBoundsException;
import ru.otus.spring.models.Question;
import ru.otus.spring.models.Student;
import ru.otus.spring.models.StudentTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunneble {
    private final IOService ioService;
    private final StudentDao studentDaoSimple;
    private final QuestionsService questionsService;
    private final AnswerConverter answerConverter;

    @Override
    public void run() {
        Student student = introduce();
        ioService.outputString("Start testing!");
        boolean result = startTest();
        StudentTest test = new StudentTest(student, result);
        ioService.outputString(test.toString());
    }

    private Student introduce() {
        ioService.outputString("Enter you name: ");
        String name = readString();
        ioService.outputString("Enter you lastname: ");
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
                ioService.outputString("Number input error");
            } catch (IndexOutOfBoundsException e) {
                ioService.outputString("Invalid option number entered");
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
