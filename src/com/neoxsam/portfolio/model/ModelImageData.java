package com.neoxsam.portfolio.model;

import org.codehaus.jackson.annotate.JsonProperty;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ModelImageData implements Parcelable {

	private String mScreenshot;

	@SuppressLint("ParcelCreator")
	public ModelImageData() {
	}

	public String getmScreenshot() {
		return mScreenshot;
	}

	@JsonProperty("picture")
	public void setmScreenshot(String mScreenshot) {
		this.mScreenshot = mScreenshot;
	}

	// *******************************************
	// Parcelable function
	// *******************************************

	public static final Parcelable.Creator<ModelImageData> CREATOR = new Parcelable.Creator<ModelImageData>() {

		@Override
		public ModelImageData createFromParcel(Parcel source) {
			return new ModelImageData(source);
		}

		@Override
		public ModelImageData[] newArray(int size) {
			return new ModelImageData[size];
		}
	};

	public ModelImageData(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mScreenshot);
	}

	public void readFromParcel(Parcel source) {
		mScreenshot = source.readString();
	}
}
