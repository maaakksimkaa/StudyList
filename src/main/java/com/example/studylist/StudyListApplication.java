package com.example.studylist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StudyListApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyListApplication.class, args);
        log.info("Swagger-ui run on: http://localhost:8989/swagger-ui/index.html");
        log.info("Swagger-ui run on: http://localhost:8989/");
    }

}
