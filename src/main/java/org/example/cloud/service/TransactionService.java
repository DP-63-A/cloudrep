package org.example.cloud.service;

import org.example.cloud.dto.TransactionDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto dto);
}

