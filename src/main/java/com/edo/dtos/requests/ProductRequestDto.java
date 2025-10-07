package com.edo.dtos.requests;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(
		 String name,
		 BigDecimal price,
		 Integer quantity,
		 UUID categoryId
		 
		) {

	
	}




