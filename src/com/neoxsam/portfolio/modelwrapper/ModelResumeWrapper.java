package com.neoxsam.portfolio.modelwrapper;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.neoxsam.portfolio.model.ModelResumeDataMain;

public class ModelResumeWrapper {

	private List<ModelResumeDataMain> mList;

	public List<ModelResumeDataMain> getmList() {
		return mList;
	}

	@JsonProperty("data")
	public void setmList(List<ModelResumeDataMain> mList) {
		this.mList = mList;
	}
}
