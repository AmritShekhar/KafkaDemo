package com.amritspring.QuoraDemo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class QuoraDemoApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();
		dotenv
				.entries()
						.forEach(
								entry -> System.setProperty(
										entry.getKey(),
										entry.getValue()
								)
						);

		SpringApplication.run(QuoraDemoApplication.class, args);
	}

}
