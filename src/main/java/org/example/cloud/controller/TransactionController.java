package org.example.cloud.controller;

import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.TransactionDto;
import org.example.cloud.dto.TransactionMessage;
import org.example.cloud.entity.Transaction;
import org.example.cloud.repository.TransactionRepository;
import org.example.cloud.service.TransactionMessageProducer;
import org.example.cloud.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @Autowired
    private TransactionMessageProducer producer;
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto dto) {
        TransactionDto saved = transactionService.createTransaction(dto);

        TransactionMessage message = new TransactionMessage(
                saved.getUserId(),
                saved.getAmount().doubleValue(),
                saved.getDate()
        );
        producer.sendMessage(message);

        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
