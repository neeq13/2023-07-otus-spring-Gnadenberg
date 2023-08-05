package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import ru.otus.spring.utils.Reader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReaderTest {

    @Test
    public void getReadToFile() {
        String path = "spring_01/src/test/resources/files/testQuestions.csv";
        Reader reader = new Reader(path);
        assertEquals(new ArrayList<>(), reader.getQuestions());
    }
}
