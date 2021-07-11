package com.bahn.casestudy.operationsite;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service // This means that the class should be instantiated to be passed into the controller.
public class OperationSiteService {
	private OperationSitesAdministrator admin;
	
	public OperationSiteService() throws IOException {
		this.admin = new OperationSitesAdministrator();
	}
	
	public OperationSite getOperationSite(String abbr) throws OperationSiteNotFoundException {
		return admin.getOperationSite(abbr);
	}
}
