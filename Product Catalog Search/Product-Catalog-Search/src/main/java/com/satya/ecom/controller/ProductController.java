package com.satya.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.satya.ecom.exception.CustomException;
import com.satya.ecom.model.Product;
import com.satya.ecom.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@GetMapping("/all-products")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = ps.getAllProducts();
		return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{filter}/{value}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable String filter, @PathVariable String value) throws CustomException{
		List<Product> products = ps.getFilteredProduct(filter, value);
		if(products == null) {
			throw new CustomException("Product Not Found");
		}
		return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
	}
}