package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final PrintStream output = System.out;
    private final Scanner input = new Scanner(System.in);

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readString() {
        return input.nextLine();
    }

    @Override
    public String readStringWithPrompt(String text) {
        outputString(text);
        return input.nextLine();
    }

    @Override
    public Object outputString(String text) {
        output.println(text);
        return null;
    }
}
