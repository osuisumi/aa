package com.haoyu.aip.qti.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class MTestSubmission implements Serializable{
	
	private static final long serialVersionUID = -2858222388749978432L;

	private List<String> candidateResponses = Lists.newArrayList();
	
	private boolean correct;

	public List<String> getCandidateResponses() {
		return candidateResponses;
	}

	public void setCandidateResponses(List<String> candidateResponses) {
		this.candidateResponses = candidateResponses;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
