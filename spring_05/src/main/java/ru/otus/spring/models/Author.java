package ru.otus.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long uuid;
    private String name;
    private String lastname;

    public String getFullName() {
        return name + " " + lastname;
    }
}
