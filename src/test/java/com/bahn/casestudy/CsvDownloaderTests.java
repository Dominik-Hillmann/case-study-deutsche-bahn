package com.bahn.casestudy;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bahn.casestudy.download.CsvDownloader;
import com.bahn.casestudy.operationsite.OperationSitesAdministrator;
import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;

@SpringBootTest
public class CsvDownloaderTests {
	static CsvDownloader client;
	static String targetDataPath = "./src/main/resources/static/operation-sites-data.csv";
	
	@BeforeAll
	public static void setupDownloader() {
		client = new CsvDownloader();
	}
	
	@AfterAll
	public static void taredownDownloader() {
		client.close();
	}
	
	@Test
	@DisplayName("Downloads and correctly saves the Betriebsstelle CSV file.")
	public void downloadsAndCreatesValidCsv() throws IOException, OperationSiteNotFoundException {
		File operationSitesData = new File(targetDataPath);
		operationSitesData.delete();
		
		Assertions.assertFalse(operationSitesData.exists());		
		client.saveNewestCsv();		
		Assertions.assertTrue(operationSitesData.exists());
		
		OperationSitesAdministrator admin = OperationSitesAdministrator.getInstance();
		try {
			admin.getOperationSite("aamp");
		} catch (CannotReadCsvException e) {
			fail();
		}
	}
}
