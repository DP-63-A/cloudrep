package org.example.cloud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tax_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxReport {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reportYear")
    private String reportYear; // "2024"
    private String status; // "SUBMITTED", "PENDING", "REJECTED"

    private LocalDate submissionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
