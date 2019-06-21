package com.boot.reactor.core.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.boot.reactor.core.service.ServiceHandler;


/*RouterFunction is the equivalent of a @RequestMapping annotation, 
* but with the major difference that router functions provide not just data, 
* but also behavior*/

@Configuration
public class ServiceRouter {

	@Bean
	public RouterFunction<ServerResponse> route(ServiceHandler serviceHandler){
		return RouterFunctions.route(
				RequestPredicates.GET("/services")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),serviceHandler::hello);
				
	}
	
}
