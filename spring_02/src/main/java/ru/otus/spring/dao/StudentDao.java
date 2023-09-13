package ru.otus.spring.dao;

import ru.otus.spring.models.Student;

public interface StudentDao {
    Student getStudent(String name, String lastName);
}
