package com.satya.studentdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satya.studentdata.dao.StudentDAO;
import com.satya.studentdata.model.Student;




@Service
@EnableCaching
public class StudentService {
	
	Logger LOGGER =  LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentDAO sd;
	
	@Autowired
	private StringRedisTemplate template;
	
	public List<Student> getAllStudent(){
		List<Student> studentList = sd.findAll();
		if(studentList != null) {
			return studentList;
		}
		
		return null;
	}
	
	public Student getStudentById(int id){
		
		String s = template.opsForValue().get(String.valueOf(id));
		if(s!= null) { 
			LOGGER.info("Data fetched from Redis Cache");
			try {
				Student student = new ObjectMapper().readValue(s,Student.class);
				return student;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} 
		}
		
		Student student = sd.findById(id); 
		
		
		if(student!= null) {
			LOGGER.info("Data fetched from DB");
			try {
				String stringObject = new ObjectMapper().writeValueAsString(student);
				template.opsForValue().set(String.valueOf(id), stringObject);
			    LOGGER.info("Data updated in Redis Cache");
			} catch (JsonProcessingException e) {
				LOGGER.error("Json Processing error occured");
				e.printStackTrace();
			}
		}
		
		return student;	
	}
	
	public boolean createStudent(Student student) {
		if(student!=null) {
			student = sd.save(student);
			LOGGER.info("Studetn data created and updated to DB");
			try {
				String stringObject = new ObjectMapper().writeValueAsString(student);
				template.opsForValue().set(String.valueOf(student.getId()), stringObject);
			    LOGGER.info("Data updated in Redis Cache");
			    return true;
			} catch (JsonProcessingException e) {
				LOGGER.error("Json Processing error occured");
				e.printStackTrace();
			}
		}
		return (sd.save(student)!= null);
		
	}
	
	public boolean deleteStudent(int id) {
		Student student = sd.findById(id);
		if(student!=null) {
			sd.delete(student);
			LOGGER.info("Student data deleted from DB");
			String s = template.opsForValue().get(String.valueOf(id));
			if(s!= null) {
				template.delete(String.valueOf(id));
				LOGGER.info("Data deleted from Redis Cache");
				return true;
			}
			LOGGER.info("Data not present in Redis Cache");
		}
		LOGGER.info("Student data not present");
		return false;
	}

}
