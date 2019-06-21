package com.boot.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boot.reactor.client.application.WebClientApplication;

@SpringBootApplication
public class BootReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootReactorApplication.class, args);
		System.out.println(new WebClientApplication().callGreetService());
	}

}
