package com.edo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edo.repositories.ProductRepository;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

	@GetMapping("category-quantity")
	public ResponseEntity<?> getCategoryQuantity (){
		
		try {
			
			var productRepository = new ProductRepository();
			var list = productRepository.groupByQuantityCategory();
			
			return ResponseEntity.ok(list);
		}
		catch(Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	
	@GetMapping("category-avgprice")
	public ResponseEntity<?> AvgPriceCategoryResDto (){
		
		try {
			
			var productRepository = new ProductRepository();
			var list = productRepository.groupByMediaCategory();
			
			return ResponseEntity.ok(list);
		}
		catch(Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	
}
