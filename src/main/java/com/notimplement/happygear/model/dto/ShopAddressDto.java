package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddressDto {
    private Integer shopAddressId;
    private String address;
    private String longitude;
    private String latitude;
}
