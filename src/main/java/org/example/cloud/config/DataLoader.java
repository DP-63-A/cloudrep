package org.example.cloud.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.cloud.entity.TaxReport;
import org.example.cloud.entity.Transaction;
import org.example.cloud.entity.User;
import org.example.cloud.repository.TaxReportRepository;
import org.example.cloud.repository.TransactionRepository;
import org.example.cloud.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TaxReportRepository taxReportRepository;

    @PostConstruct
    public void loadData() {
        User user1 = User.builder()
                .fullName("Billy Harrington")
                .email("billy@example.com")
                .build();

        User user2 = User.builder()
                .fullName("Van Darkholme")
                .email("van@example.com")
                .build();

        userRepository.saveAll(List.of(user1, user2));


        Transaction t1 = Transaction.builder()
                .user(user1)
                .type("income")
                .amount(new BigDecimal("1250.00"))
                .date(LocalDate.of(2025, 1, 15))
                .build();

        Transaction t2 = Transaction.builder()
                .user(user1)
                .type("expense")
                .amount(new BigDecimal("340.50"))
                .date(LocalDate.of(2025, 2, 3))
                .build();

        Transaction t3 = Transaction.builder()
                .user(user2)
                .type("income")
                .amount(new BigDecimal("300.00"))
                .date(LocalDate.of(2025, 3, 10))
                .build();

        transactionRepository.saveAll(List.of(t1, t2, t3));


        TaxReport r1 = TaxReport.builder()
                .user(user1)
                .year("2024")
                .status("SUBMITTED")
                .submissionDate(LocalDate.of(2025, 4, 5))
                .build();

        TaxReport r2 = TaxReport.builder()
                .user(user2)
                .year("2024")
                .status("PENDING")
                .submissionDate(null)
                .build();

        taxReportRepository.saveAll(List.of(r1, r2));
    }
}
