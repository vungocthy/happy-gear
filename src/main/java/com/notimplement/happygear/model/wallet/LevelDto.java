package com.notimplement.happygear.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto implements Serializable {
    private Long id;
    private String level;
    private BigDecimal condition;
    private String description;
    private Boolean status;
}
