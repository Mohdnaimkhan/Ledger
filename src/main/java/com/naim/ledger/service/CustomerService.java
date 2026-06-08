package com.naim.ledger.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naim.ledger.entity.Customer;
import com.naim.ledger.entity.LedgerEntry;
import com.naim.ledger.others.EntryType;
import com.naim.ledger.repo.CustomerRepository;
import com.naim.ledger.repo.LedgerEntryRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LedgerEntryRepository ledgerEntryRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public BigDecimal getBalance(Long customerId) {

        List<LedgerEntry> entries = ledgerEntryRepository.getEntriesByCustomerId(customerId);

        BigDecimal balance = BigDecimal.ZERO;

        for (LedgerEntry entry : entries) {

            if (entry.getType() == EntryType.GIVEN) {

                balance = balance.add(entry.getAmount());

            } else {

                balance = balance.subtract(entry.getAmount());

            }
        }

        return balance;
    }

}
