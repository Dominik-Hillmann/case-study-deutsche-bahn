package com.bahn.casestudy.operationsite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Instances carry the information about a "Betriebsstelle" each.
 * It automatically gets displayed correctly over the web API.
 */
@JsonPropertyOrder({"Name", "Kurzname", "Typ"})
public class OperationSite {
	/** The short code identifying an operation site. */
	private String siteCode;
	/** The name of the operation site. */
	private String name;
	/** The shorter version of the name of the operations site. */
	private String shortName;
	/** The type of the operation site. */
	private String type;
	
	
	/**
	 * Constructors with all information that will be carried back to the web API.
	 * @param siteCode The code that identifies the operation site.
	 * @param name The full name of the operation site.
	 * @param shortName The short name of the operation site.
	 * @param type The type of the operation site.
	 */
	public OperationSite(String siteCode, String name, String shortName, String type) {
		this.siteCode = siteCode;
		this.name = name;
		this.shortName = shortName;
		this.type = type;
	}
	
	
	/**
	 * The code that identifies the operation site.
	 * @return the code.
	 */
	@JsonIgnore
	public String getSiteCode() {
		return siteCode;
	}

	
	/**
	 * The full name of the operation site.
	 * @return The full name.
	 */
	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	
	/**
	 * The short name of the operation site.
	 * @return The name.
	 */
	@JsonProperty("Kurzname")
	public String getShortName() {
		return shortName;
	}

	
	/**
	 * The type of the operation site.
	 * @return The type code.
	 */
	@JsonProperty("Typ")
	public String getType() {
		return type;
	}
	
	
	@Override
	public boolean equals(Object other) {
		OperationSite otherSite;
		try {
			otherSite = (OperationSite) other;
		} catch (ClassCastException e) {
			return false;
		}
		
		return getSiteCode().equals(otherSite.getSiteCode()) &&
			getName().equals(otherSite.getName()) &&
			getShortName().equals(otherSite.getShortName()) &&
			getType().equals(otherSite.getType());
	}
	
	
	@Override
	public String toString() {
		return "Operation site: " +
			getSiteCode() + ", " +
			getName() + ", " +
			getShortName() + ", " +
			getType();
	}
}
