package dev.feryadi.backend.bayu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BayuApplication {

	public static void main(String[] args) {
		SpringApplication.run(BayuApplication.class, args);
	}

}
