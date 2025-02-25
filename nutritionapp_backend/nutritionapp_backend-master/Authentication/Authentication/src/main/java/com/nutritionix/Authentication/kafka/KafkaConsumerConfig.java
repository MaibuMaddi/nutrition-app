package com.nutritionix.Authentication.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class KafkaConsumerConfig {
@KafkaListener(topics = "userServerTopic")
public void listen(ConsumerRecord<String, String> record) {
	System.out.println("Received Message: " + record.value());
// Add your business logic to process the received message
}
}