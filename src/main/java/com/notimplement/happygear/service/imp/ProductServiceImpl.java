package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.Brand;
import com.notimplement.happygear.entities.Category;
import com.notimplement.happygear.entities.Product;
import com.notimplement.happygear.model.dto.ProductDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.BrandRepository;
import com.notimplement.happygear.repositories.CategoryRepository;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> listAll() {
        return productRepository.findAll()
                .stream()
                .map(Mapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> listAllBestSellingProduct() {
        Pageable pageable = PageRequest.of(0, 4);
        List<Product> list = productRepository.findTop4BestSellingProduct(pageable);
        return list.stream().map(Mapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    public Map<List<ProductDto>, Integer> listAllProductAndFilter(
            Integer page,
            Integer limit,
            List<Integer> brandIds,
            List<Integer> categoryIds,
            Double fromPrice,
            Double toPrice,
            String sort,
            String search) {
        if (page > 0) page--;
        else page = 0;
        Sort direction = Sort.unsorted();
        if (sort.equalsIgnoreCase("desc"))
            direction = Sort.by("price").descending();
        else if (sort.equalsIgnoreCase("asc"))
            direction = Sort.by("price").ascending();
        Pageable pageable = PageRequest.of(page, limit, direction);
        Map<List<ProductDto>, Integer> pair = new HashMap<>();
        Page<Product> products = productRepository.findProductsAndFilter(brandIds, categoryIds, fromPrice, toPrice, search, pageable);
        pair.put(
                products.stream().map(Mapper::toProductDto).collect(Collectors.toList()),
                products.getTotalPages());
        return pair;
    }

    @Override
    public List<ProductDto> listAllLatestProduct() {
        List<Product> list = productRepository.findTop4ByOrderByProductIdDesc();
        return list.stream().map(Mapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Integer id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p == null) return null;
        return Mapper.toProductDto(p);
    }

    @Override
    public ProductDto create(ProductDto b) {
        Product p = toProduct(b);
        Product res = productRepository.save(p);
        return Mapper.toProductDto(res);
    }

    @Override
    public ProductDto update(ProductDto b) {
        Product p = toProduct(b);
        Product res = productRepository.save(p);
        return Mapper.toProductDto(res);
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
}
