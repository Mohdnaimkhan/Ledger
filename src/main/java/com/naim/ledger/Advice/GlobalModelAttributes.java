package com.naim.ledger.Advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.naim.ledger.service.SettingsServices;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final SettingsServices settingsService;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {

        model.addAttribute(
                "settings",
                settingsService.getSettings()
        );
    }
}