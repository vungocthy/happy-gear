package com.notimplement.happygear.service;

import com.notimplement.happygear.model.dto.ProductDescriptionDto;

public interface ProductDescriptionService {
	ProductDescriptionDto create(ProductDescriptionDto d);
	ProductDescriptionDto update(ProductDescriptionDto d);
    ProductDescriptionDto getProductDescriptionByProductId(Integer id);
}
