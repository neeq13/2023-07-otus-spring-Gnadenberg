package ru.otus.spring.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Question {
    private String question;
    private List<String> answers = new ArrayList<>();
    private int correctAnswer;
}
