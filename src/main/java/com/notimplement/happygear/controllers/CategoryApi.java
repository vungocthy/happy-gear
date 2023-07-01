package com.notimplement.happygear.controllers;

import com.notimplement.happygear.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApi {

	private final CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<?> listAllCategory(){
		return ResponseEntity.ok(categoryService.listAll());
	}
}
