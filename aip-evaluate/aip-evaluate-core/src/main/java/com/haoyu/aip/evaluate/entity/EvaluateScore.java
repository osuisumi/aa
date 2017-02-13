package com.haoyu.aip.evaluate.entity;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class EvaluateScore extends BaseEntity{
	private static final long serialVersionUID = 3014028193010899792L;
	private String evaluateId;
	//关联的评价对象id，不是机构id
	private Relation relation;
	private String item;
	private Integer score;
	
	public EvaluateScore(String item, Integer score) {
		super();
		this.item = item;
		this.score = score;
	}
	public EvaluateScore(){
		
	}
	public EvaluateScore(String evaluateId){
		this.evaluateId = evaluateId;
	}
	
	public String getEvaluateId() {
		return evaluateId;
	}
	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	
	

}
