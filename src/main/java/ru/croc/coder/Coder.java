package ru.croc.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.croc.coder.repository.UserEventsHandler;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Coder {

	public static void main(String[] args) {
		SpringApplication.run(Coder.class, args);
	}

	@Bean
	public UserEventsHandler getUserEventsHandler(){
		return new UserEventsHandler();
	}

}
