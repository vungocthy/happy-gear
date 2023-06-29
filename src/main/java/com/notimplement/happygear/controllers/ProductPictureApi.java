package com.notimplement.happygear.controllers;

import com.notimplement.happygear.service.ProductPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pictures")
@RequiredArgsConstructor
public class ProductPictureApi {

	private final ProductPictureService service;
	
	// @GetMapping("")
	// public ResponseEntity<?> listAllProductPicture(){
	// 	return ResponseEntity.ok(service.listAll());
	// }
	
	// @GetMapping("/{id}")
	// public ResponseEntity<?> getPictureById(@PathVariable(name ="id") Integer id){
	// 	return ResponseEntity.ok(service.getById(id));
	// }

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getPictureByProductId(@PathVariable(name ="id") Integer id){
		return ResponseEntity.ok(service.getByProductId(id));
	}
	
	// @PostMapping("/create")
	// public ResponseEntity<?> createProductPicture(@Valid @RequestBody ProductPictureDto ProductPicture){
	// 	return ResponseEntity.ok(service.create(ProductPicture));
	// }
	
	// @PutMapping("/update/{id}")
	// public ResponseEntity<?> updateProductPicture(@PathVariable(name ="id") Integer id ,@Valid @RequestBody ProductPictureDto p){
	// 	p.setPictureId(id);
	// 	return ResponseEntity.ok(service.update(p));
	// }
	
	// @DeleteMapping("/delete/{id}")
	// public ResponseEntity<?> deleteProductPicture(@PathVariable(name ="id") Integer id){
	// 	return ResponseEntity.ok(service.delete(id));
	// }
}
