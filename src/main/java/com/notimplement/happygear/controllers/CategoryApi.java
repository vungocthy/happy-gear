package com.notimplement.happygear.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notimplement.happygear.model.dto.CategoryDto;
import com.notimplement.happygear.service.CategoryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApi {

	private final CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<?> listAllCategory(){
		return ResponseEntity.ok(categoryService.listAll());
	}
	
	// @GetMapping("/{id}")
	// public ResponseEntity<?> getCategoryById(@PathVariable(name ="id") Integer id){
	// 	return ResponseEntity.ok(categoryService.getById(id));
	// }
	
	// @PostMapping("/create")
	// public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto Category){
	// 	return ResponseEntity.ok(categoryService.create(Category));
	// }
	
	// @PutMapping("/update/{id}")
	// public ResponseEntity<?> updateCategory(@PathVariable(name ="id") Integer id ,@Valid @RequestBody CategoryDto cate){
	// 	cate.setCategoryId(id);
	// 	return ResponseEntity.ok(categoryService.update(cate));
	// }
	
	// @DeleteMapping("/delete/{id}")
	// public ResponseEntity<?> deleteCategory(@PathVariable(name ="id") Integer id){
	// 	return ResponseEntity.ok(categoryService.delete(id));
	// }
}
