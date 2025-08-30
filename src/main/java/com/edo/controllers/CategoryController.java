package com.edo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

	@GetMapping
	public ResponseEntity<?> getCategories() {
		// TODO
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<?> createCategory() {
		// TODO
		return ResponseEntity.ok().build();

	}
	
	

}
