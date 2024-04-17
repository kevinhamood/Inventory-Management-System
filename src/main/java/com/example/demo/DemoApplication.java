package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class DemoApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	//	System.out.print("jvhg");
	}
    @GetMapping
	public String hello() {
		return "Hello Worlllld dd";

	}


	@Bean("restTemplate")
	public RestTemplate getRestTemplate(){

return new RestTemplate();
	}



}
