package com.java_app.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoAppApplication {

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	
		System.out.println("**********************");
		System.out.println("Application is running");
		System.out.println("**********************");

	
	
	}

}
