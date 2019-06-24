package com.boot.reactor.core.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.boot.reactor.core.model.UserDetails;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics="kafka_topic", groupId="group_id",containerFactory = "kafkaListenerContainerFactory")
	public void consumeMessage(String message) {
		System.out.println("Consumed kafka_topic # " + message);
	} 
	
	
	@KafkaListener(topics="kafka_topic_json", groupId="group_json", containerFactory = "objectKafkaListenerContainerFactory")
	public void consumeMessageObject(UserDetails message) {
		System.out.println("Consumed kafka_topic_json #" + message);
		if(message instanceof UserDetails) {
			UserDetails user=(UserDetails) message;
			System.out.println("Name->" + user.getUserName());
			System.out.println("UserDept->" + user.getUserDept());
			System.out.println("user salary->" + user.getUserSalary());
			
		}
		
	} 

}
