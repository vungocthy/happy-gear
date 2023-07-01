package com.notimplement.happygear.controllers;

import com.notimplement.happygear.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandApi {

	private final BrandService brandService;
	
	@GetMapping("")
	public ResponseEntity<?> listAllBrand(){
		return ResponseEntity.ok(brandService.listAll());
	}
}
