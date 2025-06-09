package org.example.cloud.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private List<Long> transactionIds;
    private List<Long> taxReportIds;
}
