package com.example.demo.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CategoryDto {
	
	private int categoryId;
	@NotNull
	@NotEmpty(message = "Category Title cannot be Empty")
	private String categoryTitle;
	@NotNull
	@NotEmpty(message = "Description cannot be Empty")
	private String categoryDescription;

}
