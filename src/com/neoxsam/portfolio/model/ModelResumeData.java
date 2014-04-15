package com.neoxsam.portfolio.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ModelResumeData {

	private String mDate;
	private String mLocation;
	private String mName;
	private String mContent;
	
	public ModelResumeData() {
	}

	public String getmDate() {
		return mDate;
	}

	@JsonProperty("date")
	public void setmDate(String mSource) {
		this.mDate = mSource;
	}
	
	public String getmLocation() {
		return mLocation;
	}

	@JsonProperty("location")
	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}

	public String getmName() {
		return mName;
	}

	@JsonProperty("name")
	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmContent() {
		return mContent;
	}

	@JsonProperty("content")
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	
}
