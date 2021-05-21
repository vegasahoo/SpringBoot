package com.satya.studentdata.dao;



import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satya.studentdata.model.Student;

@Repository
public  interface StudentDAO extends JpaRepository<Student, Serializable> {
	
	public Student findById(int id);
}
