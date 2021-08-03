package com.satya.ecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Product {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue( strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "PRICE")
	private int price;
	
	@Column(name = "CATEGORY")
	private int categoryId;
	
	@Column(name = "BRAND")
	private int brandId;
	
	@Column(name = "COLOR")
	private int colorId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = false, updatable = false, name = "CATEGORY")
	private Category category;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = false, updatable = false, name = "BRAND")
	private Brand brand;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = false, updatable = false, name = "COLOR")
	private Color color;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = false, updatable = false, name = "SIZE")
	private Size size;

}
