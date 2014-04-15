package com.neoxsam.portfolio.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ModelProfileInformation{

	private String mInformation;

	public ModelProfileInformation() {
	}

	public String getmInformation() {
		return mInformation;
	}

	@JsonProperty("content")
	public void setmInformation(String mInformation) {
		this.mInformation = mInformation;
	}
}
