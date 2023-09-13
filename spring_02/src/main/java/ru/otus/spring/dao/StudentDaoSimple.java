package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.models.Student;

@Component
public class StudentDaoSimple implements StudentDao{
    @Override
    public Student getStudent(String name, String lastName) {
        return new Student(name, lastName);
    }
}
