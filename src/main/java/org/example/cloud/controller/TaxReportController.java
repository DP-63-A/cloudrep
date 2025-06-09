package org.example.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.example.cloud.entity.TaxReport;
import org.example.cloud.repository.TaxReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tax-reports")
@RequiredArgsConstructor
public class TaxReportController {

    private final TaxReportRepository taxReportRepository;

    @GetMapping
    public List<TaxReport> getAllReports() {
        return taxReportRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxReport> getReportById(@PathVariable Long id) {
        return taxReportRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public List<TaxReport> getReportsByUserId(@PathVariable Long userId) {
        return taxReportRepository.findByUserId(userId);
    }

}
