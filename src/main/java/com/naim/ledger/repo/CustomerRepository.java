package com.naim.ledger.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naim.ledger.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
