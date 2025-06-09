package org.example.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessage {
    private Long userId;
    private Double amount;
    private String timestamp;
}
