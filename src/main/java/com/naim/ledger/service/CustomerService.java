package com.naim.ledger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naim.ledger.entity.Customer;
import com.naim.ledger.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    

    private final CustomerRepository customerRepository;

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


}