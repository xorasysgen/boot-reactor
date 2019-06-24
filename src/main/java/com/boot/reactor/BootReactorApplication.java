package com.boot.reactor;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.boot.reactor.client.application.WebClientApplication;
import com.boot.reactor.core.model.UserDetails;


@SpringBootApplication
public class BootReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootReactorApplication.class, args);
		System.out.println(new WebClientApplication().callGreetService());
	}

	@Bean
	public ProducerFactory<String,String> producerFactoryString() {
		Map<String,Object> config=new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	@Bean
	public ProducerFactory<String,UserDetails> producerFactory() {
		Map<String,Object> config=new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	
	@Bean
	public ProducerFactory<String,Object> producerFactoryObject() {
		Map<String,Object> config=new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	@Bean("string")
	public KafkaTemplate<String,String> kafkaTemplate1() {
		return new KafkaTemplate<>(producerFactoryString());
		
	}
	
	@Bean("model")
	public KafkaTemplate<String,UserDetails> kafkaTemplate2() {
		return new KafkaTemplate<>(producerFactory());
		
	}
	
	@Bean("object")
	public KafkaTemplate<String,Object> kafkaTemplate3() {
		return new KafkaTemplate<>(producerFactoryObject());
		
	}
	
	
}
