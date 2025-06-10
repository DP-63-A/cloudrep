package org.example.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String type;
    //private String date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
