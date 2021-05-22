package com.satya.studentdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.satya.studentdata.model.Student;
import com.satya.studentdata.service.StudentService;


@RestController
public class StudentController {
	
	@Autowired
	private StudentService ss;
	
	private Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@GetMapping("/find-all-student")
	public List<Student> getAllStudent(){
		return ss.getAllStudent();
	}
	
	@GetMapping("/find-student-by-id/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		
		Student s = ss.getStudentById(id); 
		if(s != null) {
			return s; 
		}
		else {
			LOGGER.info("Student Data not present");
			return null;
		}
	}
	
	@PostMapping("/create-student")
	public String createStudent(@RequestBody Student s) {
		if(ss.createStudent(s)) {
			return "Student data added successfully";
		}
		return "Failed to add data";
	}
	
	@DeleteMapping("/delete-student/{id}")
	public String deleteStudent(@PathVariable("id") int id) {
		if(ss.deleteStudent(id)) {
			return "Student Data deleted";
		}
		return "Student data no available";
		
	}
	
}
