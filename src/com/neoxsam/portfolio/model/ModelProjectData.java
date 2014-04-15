package com.neoxsam.portfolio.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelProjectData implements Parcelable {

	private String mIcon;
	private String mTitle;
	private String mDescription;
	private String mDuration;
	private String mComany;
	private String mRole;
	private String mMarketLink;
	private String mGithubLink;
	private List<ModelImageData> mListScreenshot;

	public ModelProjectData() {
	}

	public String getmIcon() {
		return mIcon;
	}

	@JsonProperty("icon")
	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	public String getmTitle() {
		return mTitle;
	}

	@JsonProperty("title")
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmDescription() {
		return mDescription;
	}

	@JsonProperty("description")
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmDuration() {
		return mDuration;
	}

	@JsonProperty("duration")
	public void setmDuration(String mDuration) {
		this.mDuration = mDuration;
	}

	public String getmMarketLink() {
		return mMarketLink;
	}

	@JsonProperty("marketLink")
	public void setmMarketLink(String mMarketLink) {
		this.mMarketLink = mMarketLink;
	}

	public String getmGithubLink() {
		return mGithubLink;
	}

	@JsonProperty("githubLink")
	public void setmGithubLink(String mGithubLink) {
		this.mGithubLink = mGithubLink;
	}

	public List<ModelImageData> getmListScreenshot() {
		return mListScreenshot;
	}

	@JsonProperty("screenshot")
	public void setmListScreenshot(List<ModelImageData> mListScreenshot) {
		this.mListScreenshot = mListScreenshot;
	}

	public String getmComany() {
		return mComany;
	}

	@JsonProperty("company")
	public void setmComany(String mComany) {
		this.mComany = mComany;
	}

	public String getmRole() {
		return mRole;
	}

	@JsonProperty("role")
	public void setmRole(String mRole) {
		this.mRole = mRole;
	}

	
	// *******************************************
	// Parcelable function
	// *******************************************


	public static final Parcelable.Creator<ModelProjectData> CREATOR = new Parcelable.Creator<ModelProjectData>() {

		@Override
		public ModelProjectData createFromParcel(Parcel source) {
			return new ModelProjectData(source);
		}

		@Override
		public ModelProjectData[] newArray(int size) {
			return new ModelProjectData[size];
		}
	};

	public ModelProjectData(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mIcon);
		dest.writeString(this.mTitle);
		dest.writeString(this.mDescription);
		dest.writeString(this.mMarketLink);
		dest.writeString(this.mGithubLink);
		dest.writeString(this.mDuration);
		dest.writeString(this.mComany);
		dest.writeString(this.mRole);
		dest.writeList(mListScreenshot);
	}

	public void readFromParcel(Parcel source) {
		this.mIcon = source.readString();
		this.mTitle = source.readString();
		this.mDescription = source.readString();
		this.mMarketLink = source.readString();
		this.mGithubLink = source.readString();
		this.mDuration = source.readString();
		this.mComany = source.readString();
		this.mRole = source.readString();
		mListScreenshot = new ArrayList<ModelImageData>();
		source.readList(mListScreenshot, ModelImageData.class.getClassLoader());
	}

}
