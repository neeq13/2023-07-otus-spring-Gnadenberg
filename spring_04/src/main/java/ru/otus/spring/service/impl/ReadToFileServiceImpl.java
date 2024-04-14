package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.service.ReadToFileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadToFileServiceImpl implements ReadToFileService {
    private final AppConfig config;

    public ReadToFileServiceImpl(AppConfig config) {
        this.config = config;
    }

    @Override
    public List<String> getText() {
        List<String> text = new ArrayList<>();
        try (FileReader file = new FileReader(config.getPath());
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
