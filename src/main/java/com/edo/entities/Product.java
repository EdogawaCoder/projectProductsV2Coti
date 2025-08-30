package com.edo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {

	private UUID id;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private LocalDateTime createdAt;
	private Boolean active;
	private Category category;

}
