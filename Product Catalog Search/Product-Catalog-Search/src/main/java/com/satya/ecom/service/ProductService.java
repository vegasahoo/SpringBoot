package com.satya.ecom.service;

import java.util.List;

import com.satya.ecom.model.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	List<Product> getFilteredProduct(String filter, String value);
	
}
