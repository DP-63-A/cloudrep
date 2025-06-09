package org.example.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String type;
    private String date;
}
