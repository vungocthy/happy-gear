package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel {
    private Integer detailId;
    private Integer orderId;
    private Double price;
    private Integer quantity;
    private Integer productId;
    private Boolean status;
    private String productName;
    private String insuranceInfo;
    private String picture;
}
