package com.practice.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner cmd(UserRepository userRepository) {
//		return args -> {
//		userRepository.save(new User(null, "Name", "Surname", null));
//		};
//	}

}
