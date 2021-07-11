package com.bahn.casestudy.operationsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Makes class serve REST endpoints.
@RequestMapping(path = "api/betriebsstelle")
public class OperationSiteController {
	private final OperationSiteService service;
	
	@Autowired // Instanciates services outside and pass it as parameter.
	public OperationSiteController(OperationSiteService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<String> hello() {
		return List.of("Moin", "Welt");
	}
	
	
	@GetMapping(path = "/{abbr}", produces = "application/json")
	public @ResponseBody ResponseEntity<OperationSite> getAbbr(@PathVariable String abbr) throws OperationSiteNotFoundException {
		
		return new ResponseEntity<OperationSite>(
			service.getOperationSite(abbr), 
			HttpStatus.OK
		);
	}
}