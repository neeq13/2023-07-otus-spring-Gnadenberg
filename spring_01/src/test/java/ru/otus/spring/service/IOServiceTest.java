package ru.otus.spring.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IOServiceTest {

    @Test
    void getReadToFile() {
        String path = "spring_01/src/test/resources/files/testQuestions.csv";
        IOService IOService = new IOService(path);
        assertEquals(new ArrayList<>(), IOService.getText());
    }
}
