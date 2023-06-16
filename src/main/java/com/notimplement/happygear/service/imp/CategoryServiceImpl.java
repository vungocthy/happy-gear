package com.notimplement.happygear.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.notimplement.happygear.model.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.notimplement.happygear.entities.Category;
import com.notimplement.happygear.model.dto.CategoryDto;
import com.notimplement.happygear.repositories.CategoryRepository;
import com.notimplement.happygear.service.CategoryService;

import lombok.RequiredArgsConstructor;

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

	@Override
	public CategoryDto getById(Integer id) {
		var cate = categoryRepository.findById(id).orElse(null);
		return Mapper.toCategoryDto(cate);
	}

	@Override
	public CategoryDto create(CategoryDto b) {
		Category cate = toCategory(b);
		Category res = categoryRepository.save(cate);
		return Mapper.toCategoryDto(res);
	}

	@Override
	public CategoryDto update(CategoryDto b) {
		Category cate = toCategory(b);
		Category res = categoryRepository.save(cate);
		return Mapper.toCategoryDto(res);
	}

	@Override
	public CategoryDto delete(Integer id) {
		Category cate = categoryRepository.findById(id).orElse(null);
		cate.setStatus(false);
		Category res = categoryRepository.save(cate);
		return Mapper.toCategoryDto(res);
	}
	
	private Category toCategory(CategoryDto dto) {
		Category cate = new Category();
		cate.setCategoryId(dto.getCategoryId());
		cate.setCategoryName(dto.getCategoryName());
		cate.setStatus(dto.getStatus());
		return cate;
	}
}
