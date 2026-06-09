package com.naim.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.naim.ledger.entity.Settings;
import com.naim.ledger.service.SettingsServices;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsServices settingsServices;

    @GetMapping
    public String form(Model model) {

        model.addAttribute(
                "settings",
                settingsServices.getSettings());

        return "settings/settings";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Settings settings) {

        settingsServices.save(settings);

        return "redirect:/settings";
    }

    @GetMapping("/backup")
    public ResponseEntity<Resource> backupDatabase() {

        try {

            String dbPath = System.getProperty("ledger.db.path");

            File file = new File(dbPath);

            Resource resource = new FileSystemResource(file);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=ledger-backup.db")
                    .contentType(
                            MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @PostMapping("/restore")
    public String restoreDatabase(
            @RequestParam("backupFile") MultipartFile backupFile,
            RedirectAttributes redirectAttributes) {

        try {

            String appFolder = System.getenv("LOCALAPPDATA")
                    + "\\LedgerBook";

            File folder = new File(appFolder);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path restoreFile = Path.of(appFolder, "restore.db");

            Files.copy(
                    backupFile.getInputStream(),
                    restoreFile,
                    StandardCopyOption.REPLACE_EXISTING);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Backup uploaded successfully. Please restart Ledger Book.");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Restore failed: " + e.getMessage());
        }

        return "redirect:/settings";
    }
}
