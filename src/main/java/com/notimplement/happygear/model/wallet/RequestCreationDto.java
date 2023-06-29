package com.notimplement.happygear.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreationDto {
    private String customerId;
    private BigDecimal amount;
    private String description;
    private String token;
}
