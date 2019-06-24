package com.boot.reactor.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.reactor.core.model.UserDetails;

@RestController
@RequestMapping("/api")
public class PublicController {
	
	@Autowired
	@Qualifier("string")
	private KafkaTemplate<String,String>  kafkaTemplate1;
	
	@Autowired
	@Qualifier("model")
	private KafkaTemplate<String,UserDetails>  kafkaTemplate2;
	

	@Autowired
	@Qualifier("object")
	private KafkaTemplate<String,Object>  kafkaTemplate3;
	
	
	private final String TOPIC="kafka_topic";
	
	@GetMapping("/greet")
	public String greet(){
		return "Greetings from api @RestController";
	}
	
	
	@GetMapping("/kafka/{message}")
	public String kafka(@PathVariable("message") final String message) {
		kafkaTemplate3.send(TOPIC, message);
		return "message published succesfully";
	}
	 
	
	
	@PostMapping("/kafka/post")
	public String kafkaJson(@RequestBody UserDetails user) {
		kafkaTemplate3.send(TOPIC, user);
		return "Message published succesfully";
	}

}
