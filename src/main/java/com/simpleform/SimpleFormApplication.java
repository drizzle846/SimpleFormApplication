package com.simpleform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.simpleform.repository.UsersRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)

public class SimpleFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFormApplication.class, args);
	}

}
