package com.neoxsam.portfolio.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ModelProfileData{

	private String mProfilePicture;
	private List<ModelProfileInformation> mListInformation;
	
	public ModelProfileData() {
	}

	public String getmProfilePicture() {
		return mProfilePicture;
	}

	@JsonProperty("profilePicture")
	public void setmProfilePicture(String mProfilePicture) {
		this.mProfilePicture = mProfilePicture;
	}

	public List<ModelProfileInformation> getmListInformation() {
		return mListInformation;
	}

	@JsonProperty("content")
	public void setmListInformation(List<ModelProfileInformation> mListInformation) {
		this.mListInformation = mListInformation;
	}
}
