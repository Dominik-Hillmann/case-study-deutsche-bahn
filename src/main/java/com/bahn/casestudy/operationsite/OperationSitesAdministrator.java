package com.bahn.casestudy.operationsite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVParserBuilder;

/***** WICHTIG ZUM SINGLETON MACHEN *****/
// https://javabeginners.de/Design_Patterns/Singleton_-Pattern.php

public class OperationSitesAdministrator {
	private CSVReader reader;
	private final String OPERATION_SITES_DATA_PATH = "./src/main/resources/static/operation-sites-data.csv";
	
	/* private */ public List<String[]> rawSites;
	
	public OperationSitesAdministrator() throws IOException {
		rawSites = new ArrayList<String[]>();
		
		FileReader fileReader = new FileReader(OPERATION_SITES_DATA_PATH);
		
		CSVReaderBuilder builder = new CSVReaderBuilder(fileReader)
		    .withCSVParser(new CSVParserBuilder()
	    		.withSeparator(';')
	    		.build())
		    .withSkipLines(1);
		
		reader = builder.build();
		
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					rawSites.add(nextLine);
				}
			}
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public OperationSite getOperationSite(String abbr) throws OperationSiteNotFoundException {
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
}
