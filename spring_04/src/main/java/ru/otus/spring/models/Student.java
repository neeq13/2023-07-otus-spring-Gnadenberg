package ru.otus.spring.models;

import lombok.Data;

@Data
public class Student {
    private final String name;
    private final String lastname;

    public Student(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getFullName() {
        return name + " " + lastname;
    }
}
