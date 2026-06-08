package com.naim.ledger.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naim.ledger.entity.LedgerEntry;

public interface LedgerEntryRepository
                extends JpaRepository<LedgerEntry, Long> {

        List<LedgerEntry> findByCustomerId(Long customerId);

        List<LedgerEntry> findByCustomerIdOrderByEntryDateDesc(Long customerId);

        List<LedgerEntry> findTop10ByOrderByIdDesc();

        long countByCustomerId(Long customerId);

        long countByCustomer_IdAndEntryDate(
                        Long customerId,
                        LocalDate entryDate);

        List<LedgerEntry> findByCustomer_IdAndDescriptionContainingIgnoreCase(
                        Long customerId,
                        String keyword);

        List<LedgerEntry> getEntriesByCustomerId(Long customerId);

        @Query("""
                            SELECT COALESCE(SUM(e.amount),0)
                            FROM LedgerEntry e
                            WHERE e.entryDate = :date
                            AND e.type = 'GIVEN'
                        """)
        BigDecimal getTodayGiven(LocalDate date);

        @Query("""
                            SELECT COALESCE(SUM(e.amount),0)
                            FROM LedgerEntry e
                            WHERE e.entryDate = :date
                            AND e.type = 'RECEIVED'
                        """)
        BigDecimal getTodayReceived(LocalDate date);
}
