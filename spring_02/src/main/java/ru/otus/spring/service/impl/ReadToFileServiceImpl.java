package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.ReadToFileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadToFileServiceImpl implements ReadToFileService {
    private final String PATH;

    public ReadToFileServiceImpl(@Value("${path}")String path) {
        this.PATH = path;
    }

    @Override
    public List<String> getText() {
        List<String> text = new ArrayList<>();
        try (FileReader file = new FileReader(PATH);
             BufferedReader reader = new BufferedReader(file)) {
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
