package com.naim.ledger.repo;

import com.naim.ledger.entity.LedgerEntry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerEntryRepository
                extends JpaRepository<LedgerEntry, Long> {

        List<LedgerEntry> findByCustomerId(Long customerId);
}
