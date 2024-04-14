package ru.otus.spring.service;

public interface InputService {
    int readInt();

    String readString();
    String readStringWithPrompt(String text);
}
