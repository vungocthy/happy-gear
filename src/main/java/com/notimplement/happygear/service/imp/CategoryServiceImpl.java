package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.model.dto.CategoryDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.CategoryRepository;
import com.notimplement.happygear.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> listAll() {
		return categoryRepository.findAll()
			.stream()
			.map(Mapper::toCategoryDto)
			.collect(Collectors.toList());
	}

}
