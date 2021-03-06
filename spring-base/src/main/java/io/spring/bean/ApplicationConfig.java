package io.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("io.spring.bean")
public class ApplicationConfig {
    @Bean
    public User user() {
        return new User();
    }
}