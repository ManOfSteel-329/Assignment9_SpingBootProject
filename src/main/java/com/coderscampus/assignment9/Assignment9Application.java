package com.coderscampus.assignment9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.coderscampus")
public class Assignment9Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment9Application.class, args);
	}

}
