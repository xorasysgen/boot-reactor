package com.boot.reactor.client.application;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientApplication {

	private WebClient client=WebClient.create("http://localhost:8080");
	private Mono<ClientResponse>  result = client
											.get()
											.uri("/api/greet")
											.accept(MediaType.APPLICATION_JSON)
											.exchange();
	
	
	public String callGreetService() {
		return "Result >> " + result.flatMap(res->res.bodyToMono(String.class)).block();
	}
	
}
