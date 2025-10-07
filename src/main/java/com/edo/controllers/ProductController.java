package com.edo.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edo.dtos.requests.ProductRequestDto;
import com.edo.entities.Category;
import com.edo.entities.Product;
import com.edo.repositories.CategoryRepository;
import com.edo.repositories.ProductRepository;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {



	@PostMapping()
	public ResponseEntity<?> insert(@RequestBody ProductRequestDto request) {

		try {
			var product = new Product();

			var productRepository = new ProductRepository();
			var categoryRepository = new CategoryRepository();

			//
			Category category = categoryRepository.findById(request.categoryId());
			if (category == null) {
				return ResponseEntity.badRequest().body("Category not found.");
			}

			product.setId(UUID.randomUUID());
			product.setName(request.name());
			product.setPrice(request.price());
			product.setQuantity(request.quantity());
			product.setCategory(category);
			product.setCreatedAt(java.time.LocalDateTime.now());
			product.setActive(true);

			productRepository.insert(product);

			return ResponseEntity.ok(product);
		} catch (Exception e) {
			ResponseEntity.internalServerError().body(e.getMessage());
		}

		return ResponseEntity.ok().body(null);

	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ProductRequestDto request) {
		try {
			try {
				var product = new Product();

				var productRepository = new ProductRepository();

				product.setId(id);
				product.setName(request.name());
				product.setPrice(request.price());
				product.setQuantity(request.quantity());
				product.setCategory(new Category());
				product.getCategory().setId(request.categoryId());

				if (productRepository.update(product)) {
					return ResponseEntity.ok().body("Product updated successfully.");
				} else {
					return ResponseEntity.status(404).body("Product not found.");
				}

			} catch (Exception e) {
				ResponseEntity.internalServerError().body(e.getMessage());
			}

		} catch (Exception e) {
			ResponseEntity.internalServerError().body(e.getMessage());
		}
		return ResponseEntity.ok().body(null);
	}

	

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) {
		try {
			
			var productRepository = new ProductRepository();

			if (productRepository.delete(id)) {
				return ResponseEntity.ok().body("Product deleted successfully.");
			} else {
				return ResponseEntity.status(404).body("Product not found.");
			}
		} catch (Exception e) {
			ResponseEntity.internalServerError().body(e.getMessage());
		}
		
		return null;

	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		
		try {
			var productRepository = new ProductRepository();
			
			var list = productRepository.findAll();
			
			return ResponseEntity.ok().body(list);
		} 
		catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		
	}
}
