package org.example.cloud.repository;

import org.example.cloud.entity.TaxReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxReportRepository extends JpaRepository<TaxReport, Long> {
    List<TaxReport> findByUserId(Long userId);

}
