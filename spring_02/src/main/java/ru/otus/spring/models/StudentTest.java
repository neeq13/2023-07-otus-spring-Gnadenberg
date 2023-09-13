package ru.otus.spring.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StudentTest {
    private final String id;
    private final Student student;
    private final boolean result;
    private final LocalDateTime deliveryTime;

    public StudentTest(Student student, boolean result) {
        this.id = UUID.randomUUID().toString();
        this.student = student;
        this.result = result;
        this.deliveryTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "id: " + id + ",\n" +
                "student: " + student.getFullName() + ",\n" +
                "result: " + ( result ? "You passed" : "You failed") + ",\n" +
                "deliveryTime: " + deliveryTime;
    }
}
