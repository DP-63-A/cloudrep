package org.example.cloud.repository;

import org.example.cloud.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdIn(List<Long> userIds);

}
