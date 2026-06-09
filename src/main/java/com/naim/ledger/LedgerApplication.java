package com.naim.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;

@SpringBootApplication
public class LedgerApplication {

	public static void main(String[] args) {

		String appData = System.getenv("LOCALAPPDATA");

		File folder = new File(appData, "LedgerBook");

		folder.mkdirs();

		System.setProperty(
				"ledger.db.path",
				new File(folder, "ledger.db").getAbsolutePath());

		SpringApplication.run(
				LedgerApplication.class,
				args);

		System.out.println(
				"DB Path = " + System.getProperty("ledger.db.path"));
	}
}
