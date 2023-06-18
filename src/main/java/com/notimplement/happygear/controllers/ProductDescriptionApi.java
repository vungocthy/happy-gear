package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.ProductDescriptionDto;
import com.notimplement.happygear.service.ProductDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/descriptions")
@RequiredArgsConstructor
public class ProductDescriptionApi {

	private final ProductDescriptionService productDescriptionService;

	@GetMapping("")
	public ResponseEntity<?> listAllProductDescription(){
		return ResponseEntity.ok(productDescriptionService.listAll());
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductDescriptionByProductId(@PathVariable(name ="id") Integer id){
		return ResponseEntity.ok(productDescriptionService.getProductDescriptionByProductId(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDescriptionById(@PathVariable(name ="id") Integer id){
		return ResponseEntity.ok(productDescriptionService.getById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductDescription(@Valid @RequestBody ProductDescriptionDto ProductDescription){
		return ResponseEntity.ok(productDescriptionService.create(ProductDescription));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProductDescription(@PathVariable(name ="id") Integer id ,@Valid @RequestBody ProductDescriptionDto p){
		p.setProductDescriptionId(id);
		return ResponseEntity.ok(productDescriptionService.update(p));
	}
}
