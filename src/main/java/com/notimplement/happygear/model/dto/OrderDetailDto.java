package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Integer detailId;
    private Integer orderId;
    private Double price;
    private Integer quantity;
    private Integer productId;
    private Boolean status;
}
