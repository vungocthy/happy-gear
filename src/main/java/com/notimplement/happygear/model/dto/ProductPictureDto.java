package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPictureDto {
    private Integer pictureId;
    private String pictureUrl;
    private Boolean status;
    private Integer productId;
}
