package com.satya.ecom.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.satya.ecom.model.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{	
	
	@Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.code = :categoryCode")
	List<Product> findByCategory(@Param(value = "categoryCode") String categoryCode);
	
	@Query("SELECT p FROM Product p JOIN FETCH p.color WHERE p.color.code = :colorCode")
	List<Product> findByColor(@Param(value = "colorCode") String colorCode);
	
	@Query("SELECT p FROM Product p JOIN FETCH p.brand WHERE p.brand.code = :brandCode")
	List<Product> findByBrand(@Param(value = "brandCode") String brandCode);

}