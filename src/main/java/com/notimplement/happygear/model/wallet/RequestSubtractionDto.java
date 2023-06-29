package com.notimplement.happygear.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSubtractionDto implements Serializable{
    private String customerId;
    private BigDecimal amount;
    private String description;
    private String token;
    private Set<Long> walletIds;
}
