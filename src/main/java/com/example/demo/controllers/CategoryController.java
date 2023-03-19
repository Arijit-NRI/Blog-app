package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
		//POST - create category
		@PostMapping("/")
		public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
		{
			CategoryDto createCategoryDto=this.categoryService.createCategory(categoryDto);
			return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
		}
		
		//PUT - update category
		@PutMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer cid)
		{
			CategoryDto updateCategoryDto=this.categoryService.updateCategory(categoryDto, cid);
			return new ResponseEntity<>(updateCategoryDto,HttpStatus.OK);
		}
		
		//DELETE - delete category
		@DeleteMapping("/{categoryId}")
		public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid)
		{
			this.categoryService.deleteCategory(cid);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted",true),HttpStatus.OK);
		}
		
		//GET - get all categories
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAllCategory()
		{
			return ResponseEntity.ok(this.categoryService.getAllCategory());
		}
		
		//GET - get category by id
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer cid)
		{
			CategoryDto categoryDto=this.categoryService.getCategory(cid);
			return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		}
	
	
	

}
