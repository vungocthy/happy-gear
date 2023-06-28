package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.PaginationObject;
import com.notimplement.happygear.model.dto.ProductDto;
import com.notimplement.happygear.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductApi {

	private final ProductService productService;

	// @GetMapping("")
	// public ResponseEntity<?> listAllProduct(){
	// Pageable pageable = PageRequest.of(0,6);
	// Map<List<ProductDto>, Integer> listIntegerMap =
	// productService.listByPage(pageable);
	// List<Object> list = new ArrayList<>();
	// listIntegerMap.forEach((productDtos, integer) -> {
	// list.add(productDtos);
	// list.add(integer);
	// });
	// return ResponseEntity.ok(list);
	// }

	@GetMapping("/best-selling")
	public ResponseEntity<?> listBestSellingProduct() {
		return ResponseEntity.ok(productService.listAllBestSellingProduct());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> productById(@PathVariable("id") Integer id) {
		var results = productService.getById(id);
		return ResponseEntity.ok(results);
	}

	// @GetMapping("/total")
	// public ResponseEntity<?> totalProduct() {
	// 	return ResponseEntity.ok(productService.totalProduct());
	// }

	@GetMapping("")
	public ResponseEntity<?> listProductByPage(@RequestParam("page") Optional<Integer> page,
			@RequestParam("limit") Optional<Integer> limit) {

		if (page.get() > 0) {
			page = Optional.of(page.get() - 1);
		} else if (page.get() < 0) {
			page = Optional.of(0);
		}

		Pageable pageable = PageRequest.of(page.orElse(0), limit.orElse(6));
		Map<List<ProductDto>, Integer> listIntegerMap = productService.listByPage(pageable);

		PaginationObject paginationObject = new PaginationObject();

		listIntegerMap.forEach((productDtos, integer) -> {
			paginationObject.setData(productDtos);
			paginationObject.setSize(integer);
		});
		return ResponseEntity.ok(paginationObject);
	}

	// @GetMapping("/name")
	// public ResponseEntity<?> listProductByPageAndName(@RequestParam("name") String name,
	// 		@RequestParam("p") Optional<Integer> p) {
	// 	Pageable pageable = PageRequest.of(p.orElse(0), 9);
	// 	Map<List<ProductDto>, Long> listIntegerMap = productService.listByPageAndName(name, pageable);
	// 	List<Object> list = new ArrayList<>();
	// 	listIntegerMap.forEach((productDtos, integer) -> {
	// 		list.add(productDtos);
	// 		list.add(integer);
	// 	});
	// 	return ResponseEntity.ok(list);
	// }

	// @GetMapping("/search")
	// public ResponseEntity<?> listProductBySearch(@RequestParam("p") Optional<Integer> p,
	// 		@RequestParam("text") Optional<String> text) {
	// 	Pageable pageable = PageRequest.of(p.orElse(0), 9);
	// 	Map<List<ProductDto>, Integer> listIntegerMap = productService.listProductByName(text.orElse(""), pageable);
	// 	List<Object> list = new ArrayList<>();
	// 	listIntegerMap.forEach((productDtos, integer) -> {
	// 		list.add(productDtos);
	// 		list.add(integer);
	// 	});
	// 	return ResponseEntity.ok(list);
	// }

	// @GetMapping("/page")
	// public ResponseEntity<?> listProductByPageAndCatgoryAndBrand(
	// 		@RequestParam("p") Optional<Integer> p,
	// 		@RequestParam("b") Optional<Integer> brandId,
	// 		@RequestParam("c") Optional<Integer> categoryId,
	// 		@RequestParam("f") Double fromPrice,
	// 		@RequestParam("t") Double toPrice) {
	// 	Pageable pageable = PageRequest.of(p.orElse(0), 9);
	// 	Map<List<ProductDto>, Integer> listIntegerMap = productService.listByPageCategoryAndBrand(brandId.orElse(1),
	// 			categoryId.orElse(1), fromPrice, toPrice, pageable);
	// 	List<Object> list = new ArrayList<>();
	// 	listIntegerMap.forEach((productDtos, integer) -> {
	// 		list.add(productDtos);
	// 		list.add(integer);
	// 	});
	// 	return ResponseEntity.ok(list);
	// }

	@GetMapping("/latest")
	public ResponseEntity<?> listLatestProduct() {
		List<ProductDto> list = productService.listAllLatestProduct();
		return ResponseEntity.ok(list);
	}

	// @PostMapping("/create")
	// public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto Product) {
	// 	return ResponseEntity.ok(productService.create(Product));
	// }

	// @PutMapping("/update/{id}")
	// public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Integer id, @Valid @RequestBody ProductDto p) {
	// 	p.setProductId(id);
	// 	return ResponseEntity.ok(productService.update(p));
	// }

	// @DeleteMapping("/delete/{id}")
	// public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Integer id) {
	// 	return ResponseEntity.ok(productService.delete(id));
	// }

}
