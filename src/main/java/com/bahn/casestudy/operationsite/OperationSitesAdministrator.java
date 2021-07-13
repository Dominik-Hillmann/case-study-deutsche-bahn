package com.bahn.casestudy.operationsite;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.bahn.casestudy.download.CallCounter;
import com.bahn.casestudy.download.CsvDownloader;
import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;
import com.opencsv.CSVParserBuilder;

/**
 * Singleton pattern
 *
 */
public class OperationSitesAdministrator {
	private final String OPERATION_SITES_DATA= "./src/main/resources/static/operation-sites-data.csv";	
	private static final OperationSitesAdministrator SINGLETON = new OperationSitesAdministrator();
	
	private List<String[]> rawSites;
	
	private String reasonDataNotRead = null;
	
	private OperationSitesAdministrator() {
		rawSites = new ArrayList<String[]>();
		
		try {
			FileReader fileReader = new FileReader(OPERATION_SITES_DATA);
			
			CSVReaderBuilder builder = new CSVReaderBuilder(fileReader)
			    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
			    .withSkipLines(1);			
			CSVReader reader = builder.build();
			
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					rawSites.add(nextLine);
				}
			}
		} catch (CsvValidationException | IOException e) {
			reasonDataNotRead = e.getMessage();
		}
	}
	
	
	private void downloadNewerData() {
		boolean dataCheckNecessary = CallCounter.getInstance().counterDivisable();
		if (dataCheckNecessary) {
			try {
				CsvDownloader client = new CsvDownloader();
				client.saveNewestCsv();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public OperationSite getOperationSite(String abbr) throws OperationSiteNotFoundException, CannotReadCsvException {
		// The exception gets thrown here because I want the message to be displayed
		// over the API.
		if (reasonDataNotRead != null) {
			throw new CannotReadCsvException(reasonDataNotRead);
		}
		
		downloadNewerData();
		
		String searchCode = abbr.toLowerCase();
		
		String siteCode;
		String name;
		String shortName;
		String type;
		
		for (String[] rawSite : rawSites) {
			siteCode = rawSite[1];
			name = rawSite[2];
			shortName = rawSite[3];
			type = rawSite[5];
			
			if (siteCode.toLowerCase().equals(searchCode)) {
				return new OperationSite(siteCode, name, shortName, type);
			}
		}
		
		throw new OperationSiteNotFoundException(
			"Data does not contain operation site with RL100-Code " + 
			abbr + 
			"."
		);
	}
	
	
	public static OperationSitesAdministrator getInstance() {
		return SINGLETON;
	}
}
