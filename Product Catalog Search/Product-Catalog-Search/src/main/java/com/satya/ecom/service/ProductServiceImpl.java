package com.satya.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.ecom.model.Product;
import com.satya.ecom.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository pr;

	@Override
	public List<Product> getAllProducts() {
		return pr.findAll();
	}

	@Override
	public List<Product> getFilteredProduct(String filter, String value) {
	
		switch(filter.toUpperCase()){
			case "BRAND":
				return pr.findByBrand(value);
			
			case "COLOR":
				return pr.findByColor(value);
				
			case "CATEGORY":
				return pr.findByCategory(value);
			default:
				return null;
		}		
	}
}
