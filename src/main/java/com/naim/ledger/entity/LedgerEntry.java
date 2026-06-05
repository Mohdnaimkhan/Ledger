package com.naim.ledger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.naim.ledger.others.EntryType;

@Entity
@Table(name = "ledger_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate entryDate;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    private BigDecimal amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}