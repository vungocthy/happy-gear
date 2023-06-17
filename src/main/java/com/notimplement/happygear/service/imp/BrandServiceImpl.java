package com.notimplement.happygear.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.notimplement.happygear.model.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notimplement.happygear.entities.Brand;
import com.notimplement.happygear.model.dto.BrandDto;
import com.notimplement.happygear.repositories.BrandRepository;
import com.notimplement.happygear.service.BrandService;

import lombok.RequiredArgsConstructor;


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

	@Override
	public BrandDto getById(Integer id) {
		Brand brand = brandRepository.findById(id).orElse(null);
		return Mapper.toBrandDto(brand);
	}

	@Override
	public BrandDto create(BrandDto b) {
		Brand brand = toBrand(b);
		Brand res = brandRepository.save(brand);
		return Mapper.toBrandDto(res);
	}

	@Override
	public BrandDto update(BrandDto b) {
		Brand brand = toBrand(b);
		Brand res = brandRepository.save(brand);
		return Mapper.toBrandDto(res);
	}

	@Override
	public BrandDto delete(Integer id) {
		Brand brand = brandRepository.findById(id).get();
		brand.setStatus(false);
		Brand res = brandRepository.save(brand);
		return Mapper.toBrandDto(res);
	}
	
	private Brand toBrand(BrandDto dto) {
		Brand brand = new Brand();
		brand.setBrandId(dto.getBrandId());
		brand.setBrandName(dto.getBrandName());
		brand.setStatus(dto.getStatus());
		return brand;
	}
}
