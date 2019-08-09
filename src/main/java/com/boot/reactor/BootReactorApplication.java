package com.boot.reactor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.boot.reactor.client.application.WebClientApplication;
import com.boot.reactor.core.model.UserDetails;


@SpringBootApplication
@EnableKafka
@EnableDiscoveryClient
public class BootReactorApplication {

	@SuppressWarnings("unused")
	private static final Logger logger=Logger.getLogger(BootReactorApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(BootReactorApplication.class, args);
		System.out.println(new WebClientApplication().callGreetService());
	}
	

	/***********************Begin Producer Beans **************************/
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
	
	/***********************end Producer Beans **************************/
	
	/***********************begins Consumer Beans ************************/
	
	@Bean
	public ConsumerFactory<String,String> consumerFactory(){
		Map<String,Object> configs=new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		return new DefaultKafkaConsumerFactory<>(configs);
		
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> consumerfactoryListner=new ConcurrentKafkaListenerContainerFactory<>();
		consumerfactoryListner.setConsumerFactory(consumerFactory());
		return consumerfactoryListner;
		
	}
	
	
	@Bean
	public ConsumerFactory<String,UserDetails> consumerFactoryObject(){
		Map<String,Object> configs=new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new JsonDeserializer<>(UserDetails.class));
		
		
	}
	
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,UserDetails> objectKafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,UserDetails> consumerfactoryListner=new ConcurrentKafkaListenerContainerFactory<>();
		consumerfactoryListner.setConsumerFactory(consumerFactoryObject());
		return consumerfactoryListner;
		
	}




	
	
	/***********************end Consumer Beans **************************/
	
}
