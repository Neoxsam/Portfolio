package com.neoxsam.portfolio.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class ModelResumeDataMain {

	private List<ModelResumeData> mContent;
	private String mTitle;
	
	public List<ModelResumeData> getmContent() {
		return mContent;
	}

	@JsonProperty("content")
	public void setmContent(List<ModelResumeData> mContent) {
		this.mContent = mContent;
	}

	public String getmTitle() {
		return mTitle;
	}

	@JsonProperty("title")
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
}
