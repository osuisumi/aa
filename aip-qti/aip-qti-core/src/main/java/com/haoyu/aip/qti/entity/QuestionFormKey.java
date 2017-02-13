/**
 * 
 */
package com.haoyu.aip.qti.entity;

import java.io.Serializable;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lianghuahuang
 *
 */
public final class QuestionFormKey implements Serializable {

	private String testPartId;

	private String assessmentSectionId;

	private int index;

	private final String stringRepresentation;

	public QuestionFormKey(String testPartId, String assessmentSectionId,
			int index) {
		this.testPartId = testPartId;
		this.assessmentSectionId = assessmentSectionId;
		this.index = index;
		this.stringRepresentation = testPartId + ":" + assessmentSectionId
				+ ":Q" + (index + 1);
	}
	
	public QuestionFormKey(String stringRepresentation){
		String[] strArray = stringRepresentation.split(":");
		this.testPartId = strArray[0];
		this.assessmentSectionId = strArray[1];
		this.index = Integer.valueOf(StringUtils.replace(strArray[2], "Q", ""));
		this.stringRepresentation =stringRepresentation;
	}

	public String getTestPartId() {
		return testPartId;
	}

	public void setTestPartId(String testPartId) {
		this.testPartId = testPartId;
	}

	public String getAssessmentSectionId() {
		return assessmentSectionId;
	}

	public void setAssessmentSectionId(String assessmentSectionId) {
		this.assessmentSectionId = assessmentSectionId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}
	
	public boolean sameTestPart(QuestionFormKey qfk){
		if(qfk!=null){
			return this.testPartId.equals(qfk.getTestPartId());
		}
		return false;
	}
	
	public boolean sameAssessmentSection(QuestionFormKey qfk){
		if(qfk!=null){
			if(this.testPartId.equals(qfk.getTestPartId())){
				return this.assessmentSectionId.equals(qfk.getAssessmentSectionId());
			}
		}
		return false;
	}

}
