package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelDto {
    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private String insuranceInfo;
    private String picture;
    private Boolean status;
    private Integer categoryId;
    private Integer brandId;
    private List<ShopAddressDto> shopAddresses;
}
