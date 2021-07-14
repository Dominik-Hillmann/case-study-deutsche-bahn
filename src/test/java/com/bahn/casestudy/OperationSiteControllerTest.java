package com.bahn.casestudy;

import com.bahn.casestudy.operationsite.PossibleOperationSiteCodes;

import org.springframework.boot.test.context.SpringBootTest;
import com.bahn.casestudy.download.CallCounter;
import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;
import com.bahn.casestudy.operationsite.OperationSite;
import com.bahn.casestudy.operationsite.OperationSiteController;
import com.bahn.casestudy.operationsite.OperationSiteService;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class OperationSiteControllerTest {
	OperationSiteController controller;
	OperationSiteService service;
	CallCounter counter;
	OperationSite wantedSite;
	
	@BeforeEach
	public void initInstances() throws IOException {
		service = new OperationSiteService();
		controller = new OperationSiteController(service);
		counter = CallCounter.getInstance();
		wantedSite = new OperationSite("AAMP", "Hamburg Anckelmannsplatz", "Anckelmannsplatz", "Üst");
	}	
	
	
	@Test
	@DisplayName("Increments counter on call.")
	public void incrementCounterOnCall() throws CannotReadCsvException, OperationSiteNotFoundException {
		Assertions.assertEquals(0, counter.getCount());
		controller.getAbbr("aamp");
		Assertions.assertEquals(1, counter.getCount());
		controller.getAbbr("aamp");
		controller.getAbbr("aamp");
		Assertions.assertEquals(3, counter.getCount());
	}
	
	
	@Test
	@DisplayName("Returns list of possible codes on delivering no code without slash.")
	public void listOnNoSiteCode() {
		PossibleOperationSiteCodes codes = controller.noCode().getBody();
		Assertions.assertTrue(codes.length() > 0);
	}
	
	
	@Test
	@DisplayName("Returns list of possible codes on delivering no code with slash.")
	public void listOnNoSiteCodeWithSlash() {
		PossibleOperationSiteCodes codes = controller.noCodeWithSlash().getBody();
		Assertions.assertTrue(codes.length() > 0);
	}
	
	
	@Test
	@DisplayName("Returns a correct operation site code.")
	public void correctSiteCode() throws CannotReadCsvException, OperationSiteNotFoundException {
		OperationSite site = controller.getAbbr("aamp").getBody();
		Assertions.assertEquals(wantedSite, site);
	}
}
