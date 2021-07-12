package com.bahn.casestudy;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bahn.casestudy.operationsite.OperationSite;
import com.bahn.casestudy.operationsite.OperationSiteNotFoundException;
import com.bahn.casestudy.operationsite.OperationSitesAdministrator;

@SpringBootTest
public class OperationSiteAdministratorTests {
	static OperationSitesAdministrator admin;
	static OperationSite aampSite = new OperationSite(
		"AAMP",
		"Hamburg Anckelmannsplatz",
		"Anckelmannsplatz",
		"Üst"
	);
	
	@BeforeAll
	public static void setupAdministrator() throws IOException {
		admin = new OperationSitesAdministrator("operation-sites-test-data.csv");
	}
		
	@Test
	@DisplayName("Correct abbrevation")
	public void operationSiteOnCorrectAbbreveation() throws OperationSiteNotFoundException {
		OperationSite actual = admin.getOperationSite("aamp");		
		Assertions.assertEquals(actual, aampSite);
	}
		
	@Test
	@DisplayName("Case insensitive")
	public void operationSiteAbbrevationCaseInsensitive() throws OperationSiteNotFoundException {
		OperationSite actual = admin.getOperationSite("aAmP");
		Assertions.assertEquals(actual, aampSite);
	}
	
	@Test
	@DisplayName("Throws error on site not existing.")
	public void throwOnSiteNotExisting() {
		Assertions.assertThrows(
			OperationSiteNotFoundException.class, 
			() -> admin.getOperationSite("ffffff")
		);
	}
}
