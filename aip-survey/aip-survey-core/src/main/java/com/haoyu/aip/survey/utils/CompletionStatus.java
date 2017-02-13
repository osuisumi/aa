/**
 * 
 */
package com.haoyu.aip.survey.utils;

/**
 * @author Administrator
 *
 */
public enum CompletionStatus {
	UNKNOWN("unknown"),
	NOTATTEMPED("notAttemped"),
	COMPLETE("complete");	
	private String completionStatus;
	private CompletionStatus(String completionStatus){
		this.completionStatus = completionStatus;
	}
	
	public String toString(){
		return completionStatus;
	}
}
