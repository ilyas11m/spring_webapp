package com.project.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

}