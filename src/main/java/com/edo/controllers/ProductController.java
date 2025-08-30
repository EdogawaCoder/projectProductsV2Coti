package com.edo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@PostMapping
	public ResponseEntity<?> createProduct() {
		// TODO
		return ResponseEntity.ok().build();

	}

	@PutMapping
	public ResponseEntity<?> updateProduct() {
		// TODO
		return ResponseEntity.ok().build();

	}

	@DeleteMapping
	public ResponseEntity<?> deleteProduct() {
		// TODO
		return ResponseEntity.ok().build();

	}
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		// TODO
		return ResponseEntity.ok().build();
	}
}
