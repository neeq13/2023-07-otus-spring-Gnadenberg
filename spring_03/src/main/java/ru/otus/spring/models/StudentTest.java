package ru.otus.spring.models;

import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.AppConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class StudentTest {
    private final String id;
    private final Student student;
    private final boolean result;
    private final LocalDateTime deliveryTime;
    private final AppConfig appConfig;
    private final MessageSource source;


    public StudentTest(Student student, boolean result, AppConfig appConfig, MessageSource source) {
        this.id = UUID.randomUUID().toString();
        this.student = student;
        this.result = result;
        this.deliveryTime = LocalDateTime.now();
        this.appConfig = appConfig;
        this.source = source;
    }

    @Override
    public String toString() {
        String passed = source.getMessage("test.passed", new String[]{}, appConfig.getLocale());
        String failed = source.getMessage("test.failed", new String[]{}, appConfig.getLocale());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return source.getMessage("test.id", new String[]{getId()}, appConfig.getLocale()) + "\n" +
                source.getMessage("test.student", new String[]{student.getFullName()}, appConfig.getLocale()) + "\n" +
                source.getMessage("test.result", new String[]{(result ? passed : failed)}, appConfig.getLocale()) + "\n" +
                source.getMessage("test.time", new String[]{format.format(deliveryTime)}, appConfig.getLocale());
    }
}
