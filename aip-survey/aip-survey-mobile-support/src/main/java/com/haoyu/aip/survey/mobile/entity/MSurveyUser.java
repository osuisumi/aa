package com.haoyu.aip.survey.mobile.entity;

import java.io.Serializable;

public class MSurveyUser implements Serializable {
	private static final long serialVersionUID = -8213732667782340967L;

	private String state;

	private MSurvey mSurvey;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public MSurvey getmSurvey() {
		return mSurvey;
	}

	public void setmSurvey(MSurvey mSurvey) {
		this.mSurvey = mSurvey;
	}

}
