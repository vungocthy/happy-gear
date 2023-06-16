package com.notimplement.happygear.service.imp;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	private final ModelMapper mapper;
	
	@Override
	public List<CategoryDto> listAll() {
		return categoryRepository.findAll()
			.stream()
			.map(v -> mapper.map(v, CategoryDto.class))
			.toList();
	}

	@Override
	public CategoryDto getById(Integer id) {
		var cate = categoryRepository.findById(id).orElse(null);
		return mapper.map(cate, CategoryDto.class);
	}

	@Override
	public CategoryDto create(CategoryDto b) {
		Category cate = toCategory(b);
		return mapper.map(categoryRepository.save(cate), CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto b) {
		Category cate = toCategory(b);
		return mapper.map(categoryRepository.save(cate), CategoryDto.class);
	}

	@Override
	public CategoryDto delete(Integer id) {
		Category cate = categoryRepository.findById(id).orElse(null);
		cate.setStatus(false);
		return mapper.map(categoryRepository.save(cate), CategoryDto.class);
	}
	
	private Category toCategory(CategoryDto dto) {
		Category cate = new Category();
		cate.setCategoryId(dto.getCategoryId());
		cate.setCategoryName(dto.getCategoryName());
		cate.setStatus(dto.getStatus());
		return cate;
	}
}
