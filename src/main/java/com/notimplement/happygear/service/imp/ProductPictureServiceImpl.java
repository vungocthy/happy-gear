package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.model.dto.ProductPictureDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.ProductPictureRepository;
import com.notimplement.happygear.service.ProductPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService {

	private final ProductPictureRepository productPictureRepository;

	@Override
	public List<ProductPictureDto> getByProductId(Integer id) {
		return productPictureRepository.findByProductId(id)
				.stream()
				.map(Mapper::toProductPictureDto)
				.collect(Collectors.toList());
	}
}
