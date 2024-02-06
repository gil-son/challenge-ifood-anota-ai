package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ChallengeIfoodAnotaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeIfoodAnotaAiApplication.class, args);
	}

}
