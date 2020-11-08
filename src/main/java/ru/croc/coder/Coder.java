package ru.croc.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.croc.coder.repository.UserEventsHandler;

@SpringBootApplication
public class Coder {

	public static void main(String[] args) {
		SpringApplication.run(Coder.class, args);
	}

	@Bean
	public UserEventsHandler getUserEventsHandler(){
		return new UserEventsHandler();
	}

}
