package com.notimplement.happygear.service;

import com.notimplement.happygear.model.dto.ProductPictureDto;

import java.util.List;

public interface ProductPictureService {
	List<ProductPictureDto> getByProductId(Integer id);
}
