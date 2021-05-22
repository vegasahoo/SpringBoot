package com.satya.studentdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.satya.studentdata.dao.StudentDAO;
import com.satya.studentdata.model.Student;




@Service
public class StudentService {
	
	@Autowired
	private StudentDAO sd;
	
	
	public List<Student> getAllStudent(){
		List<Student> studentList = sd.findAll();
		if(studentList != null) {
			return studentList;
		}
		
		return null;
	}
	
	@Transactional
	@Cacheable(value = "student", key = "#id")
	public Student getStudentById(int id) {
		return sd.findById(id);	
	}
	
	
	@Transactional
	@CachePut(value = "student", key = "#student.id")
	public boolean createStudent(Student s) {
		return (sd.save(s)!= null);
		
	}
	
	@Transactional
	@CacheEvict(value = "student", key = "#student.id")
	public boolean deleteStudent(int id) {
		Student s = sd.findById(id);
		if(s!=null) {
			sd.delete(s);
			return true;
		}
		return false;
	}
}
