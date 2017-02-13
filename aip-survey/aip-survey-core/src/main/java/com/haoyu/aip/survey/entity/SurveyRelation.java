package com.haoyu.aip.survey.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.mapper.JsonMapper;

public class SurveyRelation extends BaseEntity {

	private static final long serialVersionUID = 1093028862812518406L;

	private String id;

	private Survey survey;

	private Relation relation;

	private int participateNum;

	private Map<String, Map<String, Integer>> choiceInteractionResults;

	private String choiceResultJson;

	public SurveyRelation() {
	}

	public SurveyRelation(Survey survey, Relation relation) {
		this.survey = survey;
		this.relation = relation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Map<String, Map<String, Integer>> getChoiceInteractionResults() {
		if (this.choiceInteractionResults != null && !this.choiceInteractionResults.isEmpty()) {
			return this.choiceInteractionResults;
		} else if (StringUtils.isNotEmpty(this.choiceResultJson)) {
			JsonMapper mapper = new JsonMapper();
			this.choiceInteractionResults = mapper.fromJson(this.choiceResultJson, mapper.contructMapType(HashMap.class, String.class, HashMap.class));
			return this.choiceInteractionResults;
		} else {
			return this.choiceInteractionResults;
		}
	}

	public void setChoiceInteractionResults(Map<String, Map<String, Integer>> choiceInteractionResults) {
		this.choiceInteractionResults = choiceInteractionResults;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public String getChoiceResultJson() {
		return choiceResultJson;
	}

	public void setChoiceResultJson(String choiceResultJson) {
		this.choiceResultJson = choiceResultJson;
	}
	
	public void setChoiceResultJson(){
		if(MapUtils.isNotEmpty(this.choiceInteractionResults)){
			this.choiceResultJson = new JsonMapper().toJson(this.choiceInteractionResults);
		}
	}
	
	public static String getId(String surveyId, String relationId) {
		return DigestUtils.md5Hex(surveyId + relationId);
	}

}