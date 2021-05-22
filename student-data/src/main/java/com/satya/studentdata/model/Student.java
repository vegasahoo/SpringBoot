package com.satya.studentdata.model;


import java.io.Serializable;

import javax.persistence.*;


import lombok.Data;

@Data
@Entity
public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "mobile_no")
	private long mobileNo;
	
	@Column(name = "email_id")
	private String emailId;
	
}