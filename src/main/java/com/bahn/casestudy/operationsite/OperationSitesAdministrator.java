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
 * This class administrates the operation sites data as a singleton.
 */
public class OperationSitesAdministrator {
	/** Path to the CSV. */
	private final String OPERATION_SITES_DATA= "./src/main/resources/static/operation-sites-data.csv";	
	/** The single instance. */
	private static final OperationSitesAdministrator SINGLETON = new OperationSitesAdministrator();
	
	/** The raw data as read from the CSV. */
	private List<String[]> rawSites;
	
	/** In case an error gets thrown this string cotains the reason why. */
	private String reasonDataNotRead = null;
	
	/**
	 * Constructor for the single instance.
	 */
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
	
	
	/**
	 * Downloads the newest possible CSV and saves it at {@link OPERATION_SITES_DATA}.
	 */
	private void downloadNewerData() {
		boolean dataCheckNecessary = CallCounter.getInstance().counterDivisable();
		
		CsvDownloader client = new CsvDownloader();
		
		if (dataCheckNecessary) {
			try { 
				client.saveNewestCsv();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				client.close();
			}
		}
	}
	
	
	/**
	 * Get an operation site by its abbreviation.
	 * @param abbr The abbreviation.
	 * @return The operation site data.
	 * @throws OperationSiteNotFoundException If there is no site using this abbreviation.
	 * @throws CannotReadCsvException If the CSV cannot be read.
	 */
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
	
	
	/**
	 * Get the single instance.
	 * @return The instance.
	 */
	public static OperationSitesAdministrator getInstance() {
		return SINGLETON;
	}
}
