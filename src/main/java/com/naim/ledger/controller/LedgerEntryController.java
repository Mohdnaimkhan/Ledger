package com.naim.ledger.controller;

import com.naim.ledger.entity.Customer;
import com.naim.ledger.entity.LedgerEntry;
import com.naim.ledger.service.CustomerService;
import com.naim.ledger.service.LedgerEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers/{customerId}/entries")
public class LedgerEntryController {

    private final CustomerService customerService;
    private final LedgerEntryService ledgerEntryService;

    @GetMapping("/new")
    public String newEntry(
            @PathVariable Long customerId,
            Model model) {

        Customer customer = customerService.getById(customerId);

        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("ledgerEntry", ledgerEntry);

        return "entry/form";
    }

    @PostMapping("/save")
    public String saveEntry(
            @PathVariable Long customerId,
            @ModelAttribute LedgerEntry ledgerEntry) {

        Customer customer = customerService.getById(customerId);

        ledgerEntry.setCustomer(customer);

        ledgerEntryService.save(ledgerEntry);

        return "redirect:/customers/" + customerId;
    }
}