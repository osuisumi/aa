/*************************************************
*Question.java
*
*2013-11-20
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.haoyu.aip.qti.entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import uk.ac.ed.ph.jqtiplus.node.content.basic.Block;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.OutcomeDeclaration;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.SingleValue;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
/**
 *
 *题目信息
 *@author 梁华璜
 */
public class Question extends BaseEntity {

	private String id;
    
	private List<String> relations;
    //标题
	private String title;
	//题目内容
	private String text;
    //题目类型
	private QuestionType quesType;
    //状态
	private String state;
	//作者
	private String author;
	//关键字
	private String keywords;
	//分值
	private double score;
	//权重 
	private float weights;
	
	private QuestionPackage questionPackage;
	
	private List<String> validateErrorInfos;
	//仅用于读取不做存储
	private String itemKey;
	
	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public Question() {
	}

	public Question(String id) {
		this.id = id;
	}
	
	public Question(AssessmentItem assessmentItem){
		this.setId(assessmentItem.getIdentifier());
		this.setTitle(assessmentItem.getTitle());
		
		OutcomeDeclaration scoreOutcomeDeclaration = assessmentItem.getOutcomeDeclaration(Identifier.assumedLegal("SCORE"));
		if(scoreOutcomeDeclaration!=null){
			SingleValue singleValue = scoreOutcomeDeclaration.getDefaultValue().getFieldValues().get(0).getSingleValue();
			this.setScore(((FloatValue)singleValue).doubleValue());
		}
	}

	public List<String> getRelations() {
		return relations;
	}

	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuestionType getQuesType() {
		return quesType;
	}
	
	public static QuestionType getQuesType(AssessmentItem assssmentItem){
		List<Block> blocks = assssmentItem.getItemBody().getBlocks();
		if(blocks!=null&&!blocks.isEmpty()){
			for(Block block:blocks){
				if(block instanceof ChoiceInteraction){
					ChoiceInteraction choiceInteraction = (ChoiceInteraction)block;
					ResponseDeclaration responseDeclaration = assssmentItem.getResponseDeclaration(choiceInteraction.getResponseIdentifier());
					if(responseDeclaration.getCardinality().equals(Cardinality.MULTIPLE)){
						return QuestionType.MULTIPLE_CHOICE;
					}else if(responseDeclaration.getCardinality().equals(Cardinality.SINGLE)){
						if(choiceInteraction.getSimpleChoices().size()==2){
							return QuestionType.TRUE_FALSE;
						}
						return QuestionType.SINGLE_CHOICE;
					}
					
				}
			}
		}
		return null;
	}
	
	public static QuestionType getQuesType(String importQuesTitle){
		String quesTypeRegex ="\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\]"; 
		if(importQuesTitle!=null){
			Pattern pattern = Pattern.compile(quesTypeRegex);
			Matcher matcher = pattern.matcher(importQuesTitle);
			if(matcher.find()){
				String quesName = matcher.group().replaceAll("\\[|\\]", "");
				return QuestionType.getQuesType(quesName);
			}
		}
		return QuestionType.SINGLE_CHOICE;
	}

	public void setQuesType(QuestionType quesType) {
		this.quesType = quesType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public float getWeights() {
		return weights;
	}

	public void setWeights(float weights) {
		this.weights = weights;
	}
	
	public boolean isValid(){
		validateErrorInfos=Lists.newArrayList();
		if(StringUtils.isEmpty(id)){
			validateErrorInfos.add("id不能为空");
			return false;
		}
		if(quesType==null||StringUtils.isEmpty(quesType.toString())){
			validateErrorInfos.add("题目类型不能为空");
			return false;
		}
		if(!EnumUtils.isValidEnum(QuestionType.class, quesType.toString())){
			validateErrorInfos.add("不支持的题目类型");
			return false;
		}		
		return validate();
	}
	
	protected  boolean validate(){return false;}
	
	public  String toXml(){return null;}

	
	public QuestionPackage getQuestionPackage() {
		return questionPackage;
	}

	public void setQuestionPackage(QuestionPackage questionPackage) {
		this.questionPackage = questionPackage;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());  
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Question){
			Question ques = (Question)obj;
			if(ques!=null&&ques.getId().equals(this.id)){
				return true;
			}
		}
		return false;
	}

	public List<String> getValidateErrorInfos() {
		return validateErrorInfos;
	}

	public void setValidateErrorInfos(List<String> validateErrorInfos) {
		this.validateErrorInfos = validateErrorInfos;
	}
}
