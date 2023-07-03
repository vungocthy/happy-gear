package com.notimplement.happygear.service;

import com.notimplement.happygear.model.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
	List<ProductDto> listAll();
	List<ProductDto> listAllLatestProduct();
	ProductDto getById(Integer id);
	ProductDto create(ProductDto pic);
	ProductDto update(ProductDto pic);
	List<ProductDto> listAllBestSellingProduct();
	Map<List<ProductDto>, Integer> listAllProductAndFilter(Integer page, Integer limit, List<Integer> brandIds, List<Integer> categoryIds, Double fromPrice,
			Double toPrice, String sort, String search);
}
