package com.bahn.casestudy.operationsite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Name", "Kurzname", "Typ"})
public class OperationSite {
	private String siteCode;
	private String name;
	private String shortName;
	private String type;
	
	public OperationSite(String siteCode, String name, String shortName, String type) {
		this.siteCode = siteCode;
		this.name = name;
		this.shortName = shortName;
		this.type = type;
	}
	
	@JsonIgnore
	public String getSiteCode() {
		return siteCode;
	}

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Kurzname")
	public String getShortName() {
		return shortName;
	}

	@JsonProperty("Typ")
	public String getType() {
		return type;
	}
}
