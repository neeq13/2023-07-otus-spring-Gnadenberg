package ru.otus.spring.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.ApplicationRunner;

@Component
public class RunApp implements CommandLineRunner {
    private final ApplicationRunner applicationRunner;

    public RunApp(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    @Override
    public void run(String... args) throws Exception {
        applicationRunner.run();
    }
}
