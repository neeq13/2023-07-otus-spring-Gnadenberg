package ru.otus.spring.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@Getter
@ConfigurationProperties(prefix = "application")
public class AppConfig {
    private final String path;
    private final Locale locale;

    @ConstructorBinding
    public AppConfig(String path, Locale locale) {
        this.path = path;
        this.locale = locale;
    }

}
