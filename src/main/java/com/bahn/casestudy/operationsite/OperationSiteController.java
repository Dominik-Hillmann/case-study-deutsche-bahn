package com.bahn.casestudy.operationsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahn.casestudy.download.CallCounter;
import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.OperationSiteNotFoundException;

/**
 * The controller that servers the wanted endpoint for the operation sites.
 * Accessed by .../api/betriebsstelle/...
 * 
 * @implNote More controllers in case of different /api/...
 */
@RestController // Makes class serve REST endpoints.
@RequestMapping(path = "api/betriebsstelle")
public class OperationSiteController {
	/** The service which the controller accesses. */
	private final OperationSiteService service;
	
	
	/**
	 * The constructor.
	 * @param service The service which the controller accesses.
	 */
	@Autowired // Instanciates services outside and pass it as parameter.
	public OperationSiteController(OperationSiteService service) {
		this.service = service;
	}	
	
	
	/**
	 * The endpoint that returns operation site information by their code.
	 * @param abbr The abbreveation by which to search.
	 * @return The response of the {@link OperationSite}.
	 * @throws OperationSiteNotFoundException If there is no operation site with this code.
	 * @throws CannotReadCsvException If the CSV which contains the operation 
	 * site cannot be read.
	 */
	@GetMapping(path = "/{abbr}", produces = "application/json")
	public @ResponseBody ResponseEntity<OperationSite> getAbbr(@PathVariable String abbr) throws 
		OperationSiteNotFoundException,
		CannotReadCsvException {
		CallCounter.getInstance().callOccured();
		
		return new ResponseEntity<OperationSite>(
			service.getOperationSite(abbr), 
			HttpStatus.OK
		);
	}
	
	/**
	 * Endpoint in case no abbreviation but a slash is given.
	 * @return Error message.
	 */
	@GetMapping(path = "/", produces = "application/json")
	public @ResponseBody ResponseEntity<PossibleOperationSiteCodes> noCodeWithSlash() {
		return new ResponseEntity<PossibleOperationSiteCodes>(
			service.getPossibleOperationSiteCodes(),
			HttpStatus.OK
		);
	}
	
	
	/**
	 * Endpoint in case no abbreviation but is given.
	 * @return Error message.
	 */
	@GetMapping(path = "", produces = "application/json")
	public @ResponseBody ResponseEntity<PossibleOperationSiteCodes> noCode() {
		return new ResponseEntity<PossibleOperationSiteCodes>(
			service.getPossibleOperationSiteCodes(),
			HttpStatus.OK
		);
	}
	
	
	/**
	 * Debugging endpoint.
	 * @return Number of times the other endpoint got accessed.
	 */
	@GetMapping(path = "/test", produces = "application/json")
	public @ResponseBody ResponseEntity<CallCounter> getCount() {		
		return new ResponseEntity<CallCounter>(
			CallCounter.getInstance(),
			HttpStatus.OK
		);	
	}
}
