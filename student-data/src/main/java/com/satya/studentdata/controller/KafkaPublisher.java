package com.satya.studentdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.satya.studentdata.model.Student;

@RestController
public class KafkaPublisher {
	
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	private final String topic = "STUDENTFEEDTOPIC";
	
	@GetMapping("/message/{name}")
	public String sendMessage(@PathVariable String name) {
		template.send(topic, "Hi "+name+" Welcome to kafka topic");		
		return "Message published";
	}
	
	@GetMapping("/object")
	public String sendObject() {
		Student student = new Student(1,"vegasahoo", 9777791775L, "shivvchandan@gmail.com");
		template.send(topic, student);
		return "Object Published";
	}
}
