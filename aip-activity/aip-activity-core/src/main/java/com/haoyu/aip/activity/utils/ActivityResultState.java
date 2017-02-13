package com.haoyu.aip.activity.utils;

public enum ActivityResultState {

	COMPLETED("completed"),NOTATTEMPED("notAttempted"),COMMITED("commited"),UNPASS("unpass");
	
	private String state;
	
	private ActivityResultState(String state){
		this.state = state;
	}
	
	public String toString(){
		return state;
	}
}
