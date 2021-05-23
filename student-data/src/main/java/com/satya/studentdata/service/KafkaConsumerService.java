package com.satya.studentdata.service;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumerService {
	
	private final String topic = "STUDENTFEEDTOPIC";
	
	@KafkaListener(topics=topic, groupId = "group_id") 
	public void consumeMessage(String message) {
		System.out.println(message);
	}
}
