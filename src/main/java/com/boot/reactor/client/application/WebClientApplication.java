package com.boot.reactor.client.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientApplication {
	
	private static final Logger logger=Logger.getLogger(WebClientApplication.class.getName());
	

	private WebClient client=WebClient.create("http://localhost:8080");
	private Mono<ClientResponse>  result = client
											.get()
											.uri("/api/greet")
											.accept(MediaType.APPLICATION_JSON)
											.exchange();
	
	
	public String callGreetService() {
		logger.log(Level.INFO,"Greetings");
		return "Result >> " + result.flatMap(res->res.bodyToMono(String.class)).block();
	}
	
	
	
}
