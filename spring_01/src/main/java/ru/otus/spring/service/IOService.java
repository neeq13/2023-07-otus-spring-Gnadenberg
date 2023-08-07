package ru.otus.spring.service;

import ru.otus.spring.service.processor.InputService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOService implements InputService {
    private final String PATH;

    public IOService(String path) {
        PATH = path;
    }

    @Override
    public List<String> getText() {
        List<String> text = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line = reader.readLine();
            while (line != null) {
                text.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }
}
