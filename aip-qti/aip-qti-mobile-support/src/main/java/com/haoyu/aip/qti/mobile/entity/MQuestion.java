package com.haoyu.aip.qti.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.aip.qti.entity.InteractionOption;
import com.haoyu.aip.qti.entity.QuestionType;

public class MQuestion implements Serializable{
	
	private static final long serialVersionUID = -5078470554376215811L;

	private String id;
	
	private String quesType;
	
	private String itemKey;
	
	private String title;
	
	private double score;
	
	private List<InteractionOption> interactionOptions = Lists.newArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuesType() {
		return quesType;
	}

	public void setQuesType(QuestionType quesType) {
		this.quesType = quesType.toString();
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public List<InteractionOption> getInteractionOptions() {
		return interactionOptions;
	}

	public void setInteractionOptions(List<InteractionOption> interactionOptions) {
		this.interactionOptions = interactionOptions;
	}

}
