package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.Product;
import com.notimplement.happygear.entities.ProductPicture;
import com.notimplement.happygear.model.dto.ProductPictureDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.ProductPictureRepository;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.service.ProductPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService{

	private final ProductPictureRepository productPictureRepository;
	private final ProductRepository productRepository;

	private ProductPicture toProductPicture(ProductPictureDto dto) {
		ProductPicture pic = new ProductPicture();
		pic.setPictureId(dto.getPictureId());
		pic.setPictureUrl(dto.getPictureUrl());
		pic.setStatus(dto.getStatus());
		pic.setProduct(getProductById(dto.getProductId()));
		return pic;
	}
	
	private Product getProductById(Integer id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<ProductPictureDto> getByProductId(Integer id) {
		return productPictureRepository.findByProductId(id)
				.stream()
				.map(Mapper::toProductPictureDto)
				.collect(Collectors.toList());
	}
}
