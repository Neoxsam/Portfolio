package com.neoxsam.portfolio.model;

public class ModelDrawerElem {
	
	public enum Function {
	    RESUME,
	    PROJECT,
	    CONTACT,
	    DEFAULT
	}
	
	private String mName;
	private Function mFunction;

	public ModelDrawerElem(String name, Function function) {
		mName = name;
		mFunction = function;
	}
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Function getmFunction() {
		return mFunction;
	}
	public void setmFunction(Function mFunction) {
		this.mFunction = mFunction;
	}
}
