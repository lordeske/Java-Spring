package com.eske.database;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Aplikacija {

    public static void main(String[] args) {
        SpringApplication.run(Aplikacija.class, args);
    }
}
