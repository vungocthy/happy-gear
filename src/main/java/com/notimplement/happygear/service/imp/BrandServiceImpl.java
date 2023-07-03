package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.model.dto.BrandDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.BrandRepository;
import com.notimplement.happygear.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

	private final BrandRepository brandRepository;
	
	@Override
	public List<BrandDto> listAll() {
		return brandRepository.findAll()
				.stream().map(Mapper::toBrandDto)
				.collect(Collectors.toList());
	}
}
