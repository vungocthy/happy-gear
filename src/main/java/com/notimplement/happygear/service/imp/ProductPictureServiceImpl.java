package com.notimplement.happygear.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.notimplement.happygear.model.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notimplement.happygear.entities.Product;
import com.notimplement.happygear.entities.ProductPicture;
import com.notimplement.happygear.model.dto.ProductPictureDto;
import com.notimplement.happygear.repositories.ProductPictureRepository;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.service.ProductPictureService;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService{

	private final ProductPictureRepository productPictureRepository;
	private final ProductRepository productRepository;
	
	@Override
	public List<ProductPictureDto> listAll() {
		return productPictureRepository.findAll()
				.stream()
				.map(Mapper::toProductPictureDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductPictureDto getById(Integer id) {
		ProductPicture pic = productPictureRepository.findById(id).get();
		return Mapper.toProductPictureDto(pic);
	}

	@Override
	public ProductPictureDto create(ProductPictureDto b) {
		ProductPicture pic = toProductPicture(b);
		ProductPicture res = productPictureRepository.save(pic);
		return Mapper.toProductPictureDto(res);
	}

	@Override
	public ProductPictureDto update(ProductPictureDto b) {
		ProductPicture pic = toProductPicture(b);
		ProductPicture res = productPictureRepository.save(pic);
		return Mapper.toProductPictureDto(res);
	}

	@Override
	public ProductPictureDto delete(Integer id) {
		ProductPicture pic = productPictureRepository.findById(id).get();
		pic.setStatus(false);
		ProductPicture res = productPictureRepository.save(pic);
		return Mapper.toProductPictureDto(res);
	}
	
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
