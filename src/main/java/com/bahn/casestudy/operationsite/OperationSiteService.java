package com.bahn.casestudy.operationsite;

import org.springframework.stereotype.Service;

import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;

/**
 * The service for getting operation sites data.
 */
@Service // This means that the class should be instantiated to be passed into the controller.
public class OperationSiteService {
	/** Administrates the CSV data. */
	private OperationSitesAdministrator admin;
	
	/**
	 * The constructor.
	 */
	public OperationSiteService() {
		this.admin = OperationSitesAdministrator.getInstance();
	}
	
	/**
	 * Get the information of the operation site using this abbreviation.
	 * @param abbr The abbreviation.
	 * @return The operation site information.
	 * @throws OperationSiteNotFoundException If there is no site using this abbreviation.
	 * @throws CannotReadCsvException If the CSV cannot be read.
	 */
	public OperationSite getOperationSite(String abbr) throws OperationSiteNotFoundException, CannotReadCsvException {
		return admin.getOperationSite(abbr);
	}
}
