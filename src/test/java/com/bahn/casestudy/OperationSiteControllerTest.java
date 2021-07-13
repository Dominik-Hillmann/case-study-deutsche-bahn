package com.bahn.casestudy;

import org.springframework.boot.test.context.SpringBootTest;

import com.bahn.casestudy.download.CallCounter;
import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;
import com.bahn.casestudy.operationsite.OperationSiteController;
import com.bahn.casestudy.operationsite.OperationSiteService;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class OperationSiteControllerTest {
	OperationSiteController controller;
	OperationSiteService service;
	CallCounter counter;
	
	@BeforeEach
	public void initInstances() throws IOException {
		service = new OperationSiteService();
		controller = new OperationSiteController(service);
		counter = CallCounter.getInstance();
	}	
	
	@Test
	public void incrementCounterOnCall() throws CannotReadCsvException, OperationSiteNotFoundException {
		Assertions.assertEquals(0, counter.getCount());
		controller.getAbbr("aamp");
		Assertions.assertEquals(1, counter.getCount());
		controller.getAbbr("aamp");
		controller.getAbbr("aamp");
		Assertions.assertEquals(3, counter.getCount());
	}
}
