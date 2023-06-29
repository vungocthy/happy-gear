package com.notimplement.happygear.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable {
    private Long id;
    private BigDecimal amount;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private String description;
    private Boolean state;
    private Boolean status;
    private Long typeId;
    private String type;
    private Long walletId;
    private String wallet;
    private Long requestId;
}
