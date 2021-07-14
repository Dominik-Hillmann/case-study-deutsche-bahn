package com.bahn.casestudy.operationsite;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A wrapper for the display of all possible operation site codes that can be
 * queried.
 */
public class PossibleOperationSiteCodes {
	/** The list of the operation site codes. */
	private List<String> codes;
	
	
	/**
	 * The constructor.
	 * @param codes The list of concrete codes.
	 */
	public PossibleOperationSiteCodes(List<String> codes) {
		this.codes = codes;
	}
	
	
	/**
	 * How many codes are currently contained here.
	 * @return The number of operation site codes.
	 */
	public int length() {
		return codes.size();
	}
	
	
	/**
	 * The codes displayed properly at the API.
	 * @return The list of operation site codes.
	 */
	@JsonProperty("Moegliche Betriebsstellencodes")
	public List<String> getCodes() {
		return codes;
	}
}
