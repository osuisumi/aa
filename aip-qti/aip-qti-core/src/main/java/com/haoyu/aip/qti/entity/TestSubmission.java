/*************************************************
*TestSubmission.java
*
*2013-12-6
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.haoyu.aip.qti.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.mapper.JsonMapper;

import uk.ac.ed.ph.jqtiplus.node.result.ItemResult;
import uk.ac.ed.ph.jqtiplus.node.result.ItemVariable;
import uk.ac.ed.ph.jqtiplus.node.result.OutcomeVariable;
import uk.ac.ed.ph.jqtiplus.node.result.ResponseVariable;
import uk.ac.ed.ph.jqtiplus.node.shared.FieldValue;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.types.ResponseData.ResponseDataType;
import uk.ac.ed.ph.jqtiplus.value.Value;

/**
 *
 *
 *@author 梁华璜
 */
public class TestSubmission extends BaseEntity {

	private static final long serialVersionUID = 4862336884509698602L;

	private String id;
	
	private String itemKey;
	
	private ResponseDataType responseDataType;
	
	private String responseData;
	
	private String completionStatus;
	
	private ResponseLegality responseLegality;
	
	private int numAttempts;
	
	private int duration;
	
	private float score;
	
	//private int sequenceIndex;
	
	private TestDeliveryUser testDeliveryUser;
	
	//仅用于前端显示不做存储
	private List<Identifier> choiceSequences;
	
	private List<String> correctResponses;
	
	private List<String> candidateResponses;
	 
	private boolean correct=false;
	
	public TestSubmission(){}
	
	public TestSubmission(ItemResult itemResult){
	//	this.id = itemResult.getIdentifier();
		this.responseDataType = ResponseDataType.STRING;
		List<ItemVariable> itemVariables = itemResult.getItemVariables();
		for(ItemVariable itemVariable:itemVariables){
			if(itemVariable instanceof OutcomeVariable){
				OutcomeVariable outcomeVariable = (OutcomeVariable)itemVariable;
				//完成状态
				if(outcomeVariable.getIdentifier().equals(Identifier.assumedLegal("completionStatus"))){
					this.completionStatus = outcomeVariable.getComputedValue().toQtiString();
				}
				else if(outcomeVariable.getIdentifier().equals(Identifier.assumedLegal("SCORE"))){
					this.score = Float.valueOf(outcomeVariable.getComputedValue().toQtiString());
				}
			}else if(itemVariable instanceof ResponseVariable){
				ResponseVariable responseVariable = (ResponseVariable)itemVariable;
				if(responseVariable.getIdentifier().equals(Identifier.assumedLegal("numAttempts"))){
					this.numAttempts = Integer.valueOf(responseVariable.getCandidateResponse().getFieldValues().get(0).getSingleValue().toQtiString());
				}else if(responseVariable.getIdentifier().equals(Identifier.assumedLegal("RESPONSE"))){
					if(responseVariable.getCandidateResponse().getFieldValues()!=null&&!responseVariable.getCandidateResponse().getFieldValues().isEmpty()){
						//单选是非
						if(responseVariable.getCardinality().isSingle()){
							candidateResponses = Lists.newArrayList();
							this.responseData = responseVariable.getCandidateResponse().getFieldValues().get(0).getSingleValue().toQtiString();
							candidateResponses.add(responseData);
						}else if(responseVariable.getCardinality().isMultiple()){
							candidateResponses = Lists.newArrayList();
							for(FieldValue fieldValue:responseVariable.getCandidateResponse().getFieldValues()){
								candidateResponses.add(fieldValue.getSingleValue().toQtiString());
							}
							JsonMapper jsonMapper = new JsonMapper();
							this.responseData = jsonMapper.toJson(candidateResponses);
						}
					}
					if(responseVariable.getChoiceSequence()!=null&&!responseVariable.getChoiceSequence().isEmpty()){
						this.choiceSequences = responseVariable.getChoiceSequence();
					}
					if(responseVariable.getCorrectResponse().getFieldValues()!=null&&!responseVariable.getCorrectResponse().getFieldValues().isEmpty()){
						correctResponses = Lists.newArrayList();
						for(FieldValue fieldValue:responseVariable.getCorrectResponse().getFieldValues()){
							correctResponses.add(fieldValue.getSingleValue().toQtiString());
						}
					}
						
				}
			}
		}
		if(correctResponses!=null&&candidateResponses!=null&&correctResponses.containsAll(candidateResponses)&&candidateResponses.containsAll(correctResponses)){
			this.correct = true;
		}
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public List<String> getCorrectResponses() {
		return correctResponses;
	}

	public void setCorrectResponses(List<String> correctResponses) {
		this.correctResponses = correctResponses;
	}

	public ResponseDataType getResponseDataType() {
		return responseDataType;
	}

	public void setResponseDataType(ResponseDataType responseDataType) {
		this.responseDataType = responseDataType;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

/*	public int getSequenceIndex() {
		return sequenceIndex;
	}

	public void setSequenceIndex(int sequenceIndex) {
		this.sequenceIndex = sequenceIndex;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getNumAttempts() {
		return numAttempts;
	}

	public void setNumAttempts(int numAttempts) {
		this.numAttempts = numAttempts;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public ResponseLegality getResponseLegality() {
		return responseLegality;
	}

	public void setResponseLegality(ResponseLegality responseLegality) {
		this.responseLegality = responseLegality;
	}

	public TestDeliveryUser getTestDeliveryUser() {
		return testDeliveryUser;
	}

	public void setTestDeliveryUser(TestDeliveryUser testDeliveryUser) {
		this.testDeliveryUser = testDeliveryUser;
	}

	public List<Identifier> getChoiceSequences() {
		return choiceSequences;
	}

	public void setChoiceSequences(List<Identifier> choiceSequences) {
		this.choiceSequences = choiceSequences;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public List<String> getCandidateResponses() {
		return candidateResponses;
	}

	public void setCandidateResponses(List<String> candidateResponses) {
		this.candidateResponses = candidateResponses;
	}
	
	
}
