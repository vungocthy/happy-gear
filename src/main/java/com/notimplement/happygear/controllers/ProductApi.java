package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.PaginationObject;
import com.notimplement.happygear.model.dto.ProductDto;
import com.notimplement.happygear.service.ProductPictureService;
import com.notimplement.happygear.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;
    private final ProductPictureService productPictureService;

    @GetMapping("/best-selling")
    public ResponseEntity<?> listBestSellingProduct() {
        return ResponseEntity.ok(productService.listAllBestSellingProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> productById(@PathVariable("id") Integer id) {
        var results = productService.getById(id);
        return ResponseEntity.ok(results);
    }

    @GetMapping("")
    public ResponseEntity<?> listProductAndFilter(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit,
            @RequestParam("search") Optional<String> search,
            @RequestParam("brandIds") Optional<List<Integer>> brandIds,
            @RequestParam("categoryIds") Optional<List<Integer>> categoryIds,
            @RequestParam("fromPrice") Optional<Double> fromPrice,
            @RequestParam("toPrice") Optional<Double> toPrice,
            @RequestParam("sortBy") Optional<String> sortBy) {
        Map<List<ProductDto>, Integer> listIntegerMap = productService.listAllProductAndFilter(
                page.orElse(0),
                limit.orElse(8),
                brandIds.orElse(null),
                categoryIds.orElse(null),
                fromPrice.orElse(0.0),
                toPrice.orElse(Double.MAX_VALUE),
                sortBy.orElse("asc"),
                search.orElse("")
        );
        PaginationObject paginationObject = new PaginationObject();
        listIntegerMap.forEach((productDtos, integer) -> {
            paginationObject.setData(productDtos);
            paginationObject.setSize(integer);
        });
        return ResponseEntity.ok(paginationObject);
    }

    @GetMapping("/{id}/pictures")
    public ResponseEntity<?> listProductPictureByProductId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productPictureService.getByProductId(id));
    }

    @GetMapping("/latest")
    public ResponseEntity<?> listLatestProduct() {
        List<ProductDto> list = productService.listAllLatestProduct();
        return ResponseEntity.ok(list);
    }
}
