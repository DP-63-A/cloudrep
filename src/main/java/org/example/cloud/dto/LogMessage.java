package org.example.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {
    private String level; // INFO, ERROR, DEBUG
    private String message;
    private LocalDateTime timestamp;
}
