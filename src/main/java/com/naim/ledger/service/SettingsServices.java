package com.naim.ledger.service;

import org.springframework.stereotype.Service;

import com.naim.ledger.entity.Settings;
import com.naim.ledger.repo.SettingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingsServices {

    private final SettingsRepository settingsRepository;

    public Settings getSettings() {
        return settingsRepository.findById(1L).orElse(new Settings());
    }

    public Settings save(Settings settings) {
        settings.setId(1L);
        return settingsRepository.save(settings);
    }

}
