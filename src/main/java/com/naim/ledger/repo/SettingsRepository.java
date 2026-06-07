package com.naim.ledger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.naim.ledger.entity.Settings;

public interface SettingsRepository extends JpaRepository<Settings,Long>  {
    
}
