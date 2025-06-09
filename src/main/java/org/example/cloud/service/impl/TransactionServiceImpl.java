package org.example.cloud.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.TransactionDto;
import org.example.cloud.entity.Transaction;
import org.example.cloud.entity.User;
import org.example.cloud.repository.TransactionRepository;
import org.example.cloud.repository.UserRepository;
import org.example.cloud.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public TransactionDto createTransaction(TransactionDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        Transaction transaction = Transaction.builder()
                .user(user)
                .amount(dto.getAmount())
                .type(dto.getType())
                .date(LocalDate.now())
                .build();

        Transaction saved = transactionRepository.save(transaction);

        return TransactionDto.builder()
                .id(saved.getId())
                .userId(saved.getUser().getId())
                .amount(saved.getAmount())
                .type(saved.getType())
                .date(saved.getDate().toString())
                .build();
    }
}