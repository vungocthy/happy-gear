package com.notimplement.happygear.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
	private final ModelMapper mapper;
	
	@Override
	public List<ProductPictureDto> listAll() {
		return productPictureRepository.findAll()
				.stream()
				.map(v -> mapper.map(v, ProductPictureDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductPictureDto getById(Integer id) {
		return mapper.map(productPictureRepository.findById(id).get(), ProductPictureDto.class);
	}

	@Override
	public ProductPictureDto create(ProductPictureDto b) {
		ProductPicture pic = toProductPicture(b);
		return mapper.map(productPictureRepository.save(pic), ProductPictureDto.class);
	}

	@Override
	public ProductPictureDto update(ProductPictureDto b) {
		ProductPicture pic = toProductPicture(b);
		return mapper.map(productPictureRepository.save(pic), ProductPictureDto.class);
	}

	@Override
	public ProductPictureDto delete(Integer id) {
		ProductPicture pic = productPictureRepository.findById(id).get();
		pic.setStatus(false);
		return mapper.map(productPictureRepository.save(pic), ProductPictureDto.class);
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
				.map(v -> mapper.map(v, ProductPictureDto.class))
				.collect(Collectors.toList());
	}
}
