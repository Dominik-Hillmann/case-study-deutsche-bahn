package com.bahn.casestudy.operationsite;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;

@Service // This means that the class should be instantiated to be passed into the controller.
public class OperationSiteService {
	private OperationSitesAdministrator admin;
	
	public OperationSiteService() throws IOException {
		this.admin = OperationSitesAdministrator.getInstance();
	}
	
	public OperationSite getOperationSite(String abbr) throws OperationSiteNotFoundException, CannotReadCsvException {
		return admin.getOperationSite(abbr);
	}
}
