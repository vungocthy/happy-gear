package com.notimplement.happygear.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notimplement.happygear.entities.Brand;
import com.notimplement.happygear.entities.Category;
import com.notimplement.happygear.entities.Product;
import com.notimplement.happygear.model.dto.ProductDto;
import com.notimplement.happygear.model.mapper.ProductMapper;
import com.notimplement.happygear.repositories.BrandRepository;
import com.notimplement.happygear.repositories.CategoryRepository;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.service.ProductService;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;
	
	@Override
	public List<ProductDto> listAll() {
		List<Product> list = productRepository.findAll();
		List<ProductDto> listDto = new ArrayList<>();
		list.forEach(v -> listDto.add(ProductMapper.toProductDto(v)));
		return listDto;
	}

	@Override
	public Map<List<ProductDto>, Integer> listByPage(Pageable pageable){
		Map<List<ProductDto>, Integer> pair = new HashMap<List<ProductDto>, Integer>();
		Page<Product> pageList = productRepository.findAll(pageable);
		pair.put(pageList.stream().map(ProductMapper::toProductDto).collect(Collectors.toList()), pageList.getTotalPages());
		return pair;
	}

	@Override
	public Map<List<ProductDto>, Integer> listByPageCategoryAndBrand(Integer brandId, Integer categoryId, Double fromPrice, Double toPrice, Pageable pageable){
		// Map<List<ProductDto>, Integer> pair = new HashMap<List<ProductDto>, Integer>();
		// Page<Product> pageList = repo.findAllProductWithFilter(brandId,categoryId,fromPrice,toPrice, pageable);
		// pair.put(pageList.stream().map(ProductMapper::toProductDto).collect(Collectors.toList()), pageList.getTotalPages());
		// return pair;
		return null;
	}
	
	@Override
	public Map<List<ProductDto>, Long> listByPageAndName(String productName, Pageable pageable) {
		Map<List<ProductDto>, Long> pair = new HashMap<List<ProductDto>, Long>();
		Page<Product> pageList = productRepository.findByProductNameContaining(productName, pageable);
		pair.put(pageList.stream().map(ProductMapper::toProductDto).collect(Collectors.toList()), pageList.getTotalElements());
		return pair;
	}

	@Override
	public Map<List<ProductDto>, Integer> listProductByName(String productName, Pageable pageable){
		Map<List<ProductDto>, Integer> pair = new HashMap<List<ProductDto>, Integer>();
		Page<Product> pageList = productRepository.findByProductNameContainingIgnoreCase(productName, pageable);
		pair.put(pageList.stream().map(ProductMapper::toProductDto).collect(Collectors.toList()), pageList.getTotalPages());
		return pair;
	}

	@Override
	public List<ProductDto> listAllProductWithMinQuantity(){
		// List<Product> list = repo.findTop5AndOrderByQuantityAsc();
		// return list.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
		return null;
	}

	@Override
	public List<ProductDto> listAllLatestProduct(){
		// List<Product> list = repo.findLatestProduct();
		// return list.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
		return null;
	}

	@Override
	public ProductDto getById(Integer id) {
		return ProductMapper.toProductDto(productRepository.findById(id).get());
	}

	@Override
	public ProductDto create(ProductDto b) {
		Product p = toProduct(b);
		return ProductMapper.toProductDto(productRepository.save(p));
	}

	@Override
	public ProductDto update(ProductDto b) {
		Product p = toProduct(b);
		return ProductMapper.toProductDto(productRepository.save(p));
	}

	@Override
	public ProductDto delete(Integer id) {
		Product p = productRepository.findById(id).get();
		p.setStatus(false);
		return ProductMapper.toProductDto(productRepository.save(p));
	}
	
	private Product toProduct(ProductDto dto) {
		Product p = new Product();
		p.setProductId(dto.getProductId());
		p.setProductName(dto.getProductName());
		p.setPrice(dto.getPrice());
		p.setQuantity(dto.getQuantity());
		p.setInsuranceInfo(dto.getInsuranceInfo());
		p.setStatus(dto.getStatus());
		p.setCategory(getCateById(dto.getCategoryId()));
		p.setBrand(getBrandById(dto.getBrandId()));
		return p;
	}
	
	private Category getCateById(Integer id) {
		return categoryRepository.findById(id).get();
	}
	
	private Brand getBrandById(Integer id) {
		return brandRepository.findById(id).get();
	}
	
	public Long totalProduct() {
		Long total = productRepository.count();
		return total;
	}

	@Override
	public Product getProductById(Integer id) {
		Product p = productRepository.findById(id).orElse(null);
		return p;
	}

	@Override
	public Map<List<Product>, Integer> listAllProductByPage(Pageable pageable) {
		Map<List<Product>, Integer> pair = new HashMap<>();
		Page<Product> pageList = productRepository.findAll(pageable);
		pair.put(pageList.stream().collect(Collectors.toList()), pageList.getTotalPages());
		return pair;
	}
}
