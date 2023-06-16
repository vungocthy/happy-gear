package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.BrandDto;
import com.notimplement.happygear.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandApi {

	private final BrandService brandService;
	
	@GetMapping("")
	public ResponseEntity<?> listAllBrand(){
		return ResponseEntity.ok(brandService.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBrandById(@PathVariable(name ="id") Integer id){
		return ResponseEntity.ok(brandService.getById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createBrand(@Valid @RequestBody BrandDto brand){
		return ResponseEntity.ok(brandService.create(brand));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBrand(@PathVariable(name ="id") Integer id ,@Valid @RequestBody BrandDto brand){
		brand.setBrandId(id);
		return ResponseEntity.ok(brandService.update(brand));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable(name ="id") Integer id){
		return ResponseEntity.ok(brandService.delete(id));
	}
}
