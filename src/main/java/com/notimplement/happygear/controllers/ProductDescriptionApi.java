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

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductDescriptionByProductId(@PathVariable(name ="id") Integer id){
		ProductDescriptionDto res = productDescriptionService.getProductDescriptionByProductId(id);
		if(res == null){
			return ResponseEntity.ok("No description found");
		}
		return ResponseEntity.ok(productDescriptionService.getProductDescriptionByProductId(id));
	}

	@PostMapping("/create")
	public ResponseEntity<?> createProductDescription(@Valid @RequestBody ProductDescriptionDto ProductDescription){
		return ResponseEntity.ok(productDescriptionService.create(ProductDescription));
	}
}
