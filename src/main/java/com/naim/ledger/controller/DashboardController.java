package com.naim.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.naim.ledger.service.CustomerService;
import com.naim.ledger.service.LedgerEntryService;

@Controller

public class DashboardController {

        @Autowired
        private CustomerService customerService;
        @Autowired
        private LedgerEntryService ledgerEntryService;

        @GetMapping("/")
        public String dashboard(Model model) {

                model.addAttribute(
                                "totalCustomers",
                                customerService.getAllCustomers().size());

                model.addAttribute(
                                "totalGiven",
                                ledgerEntryService.getTotalGiven());

                model.addAttribute(
                                "totalReceived",
                                ledgerEntryService.getTotalReceived());

                model.addAttribute(
                                "netBalance",
                                ledgerEntryService.getNetBalance());
                model.addAttribute(
                                "recentEntries",
                                ledgerEntryService.getRecentEntries());
                model.addAttribute("totalEntries",
                                ledgerEntryService.getTotalEntriesCount());
                model.addAttribute(
                                "todayGiven",
                                ledgerEntryService.getTodayGiven());

                model.addAttribute(
                                "todayReceived",
                                ledgerEntryService.getTodayReceived());
                return "dashboard";

        }

        @GetMapping("/about")
        public String getAbout() {
                return "about";
        }

}
