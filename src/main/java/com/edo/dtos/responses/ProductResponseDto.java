package com.edo.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.edo.entities.Category;

public record ProductResponseDto(
		UUID id,
		String name,
		BigDecimal price,
		Integer quantity,
		LocalDateTime createdAt,
		Category category
		) {

}
