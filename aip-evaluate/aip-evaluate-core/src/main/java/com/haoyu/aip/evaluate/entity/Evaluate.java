package com.haoyu.aip.evaluate.entity;

import java.util.ArrayList;
import java.util.List;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Evaluate extends BaseEntity{
	private static final long serialVersionUID = 5783237844392708374L;
	private String evaluateEntityId;
	private String evaluateEntityType;
	private List<EvaluateScore> evaluateScores = new ArrayList<EvaluateScore>();
	private String suggest;
	private String status;
	private Integer agreeNum;
	private Relation relation = new Relation();
	
	public Evaluate(){}
	
	public Evaluate(String evaluateEntityId){
		this.evaluateEntityId = evaluateEntityId;
	}
	
	public String getEvaluateEntityId() {
		return evaluateEntityId;
	}
	public void setEvaluateEntityId(String evaluateEntityId) {
		this.evaluateEntityId = evaluateEntityId;
	}
	public List<EvaluateScore> getEvaluateScores() {
		return evaluateScores;
	}
	public void setEvaluateScores(List<EvaluateScore> evaluateScores) {
		this.evaluateScores = evaluateScores;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}

	public String getEvaluateEntityType() {
		return evaluateEntityType;
	}

	public void setEvaluateEntityType(String evaluateEntityType) {
		this.evaluateEntityType = evaluateEntityType;
	}
	
}
