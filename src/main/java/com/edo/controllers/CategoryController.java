package com.edo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edo.repositories.CategoryRepository;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

	@GetMapping
	public ResponseEntity<?> getAll() {

		try {
			var categoryRepository = new CategoryRepository();
			var list = categoryRepository.findAll();

			return ResponseEntity.ok().body(list);
		} catch (Exception e) {


			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@PostMapping
	public ResponseEntity<?> createCategory() {
		// TODO
		return ResponseEntity.ok().build();

	}

}
