package com.bazapodatak1.database1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.bazapodatak1.database1.entity")

public class Database11Application {

	public static void main(String[] args) {
		SpringApplication.run(Database11Application.class, args);
	}

}
