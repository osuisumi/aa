package com.haoyu.aip.assignment.utils;

public enum AssignmentUserState {

	COMMIT("commit"), COMPLETE("complete");
	
	private String state;

	private AssignmentUserState(String state){
		this.state = state;
	}
	
	public String toString(){
		return this.state;
	}
}
