package com.satya.ecom.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Category {
	
	
	@Id
	@Column(name = "ID")
	@GeneratedValue( strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "CODE")
	private String code;
	

}
