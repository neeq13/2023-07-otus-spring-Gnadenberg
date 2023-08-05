package ru.otus.spring.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final String PATH;

    public Reader(String path) {
        PATH = path;
    }

    public List<String> getQuestions() {
        List<String> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                questions.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }
}
