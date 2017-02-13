package com.haoyu.aip.evaluate.entity;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Agree extends BaseEntity{
	private static final long serialVersionUID = -1385251211046255393L;
	private Relation relation;

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	
}
