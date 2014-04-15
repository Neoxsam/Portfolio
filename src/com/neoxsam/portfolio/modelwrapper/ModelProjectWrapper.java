package com.neoxsam.portfolio.modelwrapper;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.neoxsam.portfolio.model.ModelProjectData;

public class ModelProjectWrapper {

	private List<ModelProjectData> mList;

	public List<ModelProjectData> getmList() {
		return mList;
	}

	@JsonProperty("data")
	public void setmList(List<ModelProjectData> mList) {
		this.mList = mList;
	}
}
