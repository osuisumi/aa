package com.haoyu.aip.survey.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.survey.dao.ISurveyQuestionDao;
import com.haoyu.aip.survey.entity.Choice;
import com.haoyu.aip.survey.entity.ChoiceGroup;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.service.ISurveyQuestionService;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;


@Service("surveyQuestionService")
public class SurveyQuestionServiceImpl implements ISurveyQuestionService {
	@Resource
	private ISurveyQuestionDao surveyQuestionDao;
	
	@Resource
	private ApplicationContext applicationContext;
	
	@Resource
	private ISurveyRelationService surveyRelationService;

	@Override
	public Response createSurveyQuestion(SurveyQuestion surveyQuestion) {
		if(surveyQuestion == null){
			return Response.failInstance().responseMsg("create surveyQuestion fail! surveyQuestion is null");
		}
		if(StringUtils.isEmpty(surveyQuestion.getId())){
			surveyQuestion.setId(Identities.uuid2());
		}
		Response response =  surveyQuestionDao.insertSurveyQuestion(surveyQuestion)>0?Response.successInstance():Response.failInstance();
		if(response.isSuccess()){
			response.responseData(surveyQuestion);
		}
		return response;
	}

	@Override
	public Response updateSurveyQuestion(SurveyQuestion surveyQuestion) {
		return surveyQuestionDao.updateSurveyQuestion(surveyQuestion)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteSurveyQuestion(SurveyQuestion surveyQuestion) {
		return surveyQuestionDao.deleteSurveyQuestionByLogic(surveyQuestion)>0?Response.successInstance():Response.failInstance();
	}
	
	public Response deleteSurveyQuestionPhysics(SurveyQuestion surveyQuestion){
		return surveyQuestionDao.deleteSurveyQuestionByPhysics(surveyQuestion)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public SurveyQuestion findSurveyQuestionById(String id) {
		return surveyQuestionDao.selectSurveyQuestionById(id);
	}

	@Override
	public List<SurveyQuestion> findSurveyQuestions(SurveyQuestion surveyQuestion) {
		return findSurveyQuestions(surveyQuestion,null);
	}

	@Override
	public List<SurveyQuestion> findSurveyQuestions(SurveyQuestion surveyQuestion, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		//todo 设置参数
		return findSurveyQuestions(parameter,pageBounds);
	}

	@Override
	public List<SurveyQuestion> findSurveyQuestions(Map<String, Object> parameter) {
		return surveyQuestionDao.findAll(parameter);
	}

	@Override
	public List<SurveyQuestion> findSurveyQuestions(Map<String, Object> parameter, PageBounds pageBounds) {
		return surveyQuestionDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response createSurveyQuestions(Survey survey) {
		Response response = Response.successInstance();
		for(SurveyQuestion surveyQuestion:survey.getQuestions()){
			surveyQuestion.setSurvey(survey);
			this.createSurveyQuestion(surveyQuestion);
		}
		return response;
	}

	@Override
	public Response importSurveyQuestionsFromString(String input,String surveyId) {
		input = input.trim();
		if(StringUtils.isAnyEmpty(input,surveyId)){
			return Response.failInstance();
		}
		String [] lines = input.split("\n");
		
		SurveyQuestion temp = null;
		List<SurveyQuestion> questions = Lists.newArrayList();
		
		for(int i=0;i<lines.length;i++){
			int type = getType(lines[i],temp);
			if(type>0){
				SurveyQuestion sq = new SurveyQuestion();
				sq.setSurvey(new Survey(surveyId));
				sq.setChoiceGroups(generateOneGroupList());
				sq.setContent(lines[i]);
				sq.setTitle(lines[i]);
				if(type == LineType.SINGLE_QUESTION){
					sq.setType(SurveyQuestion.QuesType.SINGLECHOICE);
				}
				else if(type == LineType.MUTI_QUESTION){
					sq.setType(SurveyQuestion.QuesType.MULTIPLECHOICE);
				}else if(type == LineType.TEXT_ENTRY){
					sq.setType(SurveyQuestion.QuesType.TEXTENTRY);
				}
				if(temp != null){
					questions.add(temp);
				}
				temp = sq;
			}
			else if(type == LineType.CHOICE){
				if(temp != null && !temp.getType().equals(SurveyQuestion.QuesType.TEXTENTRY)){
					Choice c = new Choice();
					c.setId(Identities.uuid2());
					c.setContent(lines[i]);
					temp.getChoiceGroups().get(0).getChoices().add(c);
				}
			}else if(type == LineType.EMPTY_LINE){
				if(temp!=null){
					questions.add(temp);
					temp = null;
				}
			}
		}
		if(temp!=null){
			questions.add(temp);
		}
		
		Response response = Response.failInstance();
		if(CollectionUtils.isNotEmpty(questions)){
			questions = filter(questions);
			for(SurveyQuestion sq:questions){
				response = this.createSurveyQuestion(sq);
			}
		}
		return response;
		
	}
	
	private List<SurveyQuestion> filter(List<SurveyQuestion> questions){
		List<SurveyQuestion> result = Lists.newArrayList();
		for(SurveyQuestion sq:questions){
			//如果是选择题，选项大于等于两个才合法
			//如果单选一个选项都没有，并且名字中不包含[单选题]，把该题目转换成简答题
			if(sq.getType().equals(SurveyQuestion.QuesType.SINGLECHOICE)||sq.getType().equals(SurveyQuestion.QuesType.MULTIPLECHOICE)){
				if(CollectionUtils.isNotEmpty(sq.getChoiceGroups())){
					if(CollectionUtils.isNotEmpty(sq.getChoiceGroups().get(0).getChoices()) && sq.getChoiceGroups().get(0).getChoices().size()>=2){
						result.add(sq);
					}
					if(sq.getType().equals(SurveyQuestion.QuesType.SINGLECHOICE) && !sq.getTitle().contains("[单选题]") && CollectionUtils.isEmpty(sq.getChoiceGroups().get(0).getChoices())){
						sq.setType(SurveyQuestion.QuesType.TEXTENTRY);
						result.add(sq);
					}
				}
			}else{
				result.add(sq);
			}
			//去掉标题中[xxx题] 的匹配字符
			String title = sq.getTitle().replaceAll("\\[单选题\\]", "").replaceAll("\\[多选题\\]", "").replaceAll("\\[简答题\\]", "");
			//去掉开头的^[0-9]*、
			title = title.replaceAll("^[0-9]*、", "");
			sq.setTitle(title);
		}
		return result;
	}
	
	private int getType(String line,SurveyQuestion temp){
		/*
		 * 判别题目还是选项规则
		 * 1.改行匹配到[xxx题目] 直接判别为题目
		 * 
		 * 以数字和、开头，判为单选题
		 * 
		 * 2.以数字开头，如果当前题目为空或者不需要选项 判别为 题目
		 * 		
		 * 
		 * 3.空白行，temp加入到列表并，清空当前题目
		 * 
		 * 其他的权当选项
		 */
		line = line.trim();
		if(line.equals("")){
			return LineType.EMPTY_LINE;
		}
		else if(line.contains("[单选题]")){
			return LineType.SINGLE_QUESTION;
		}else if(line.contains("[多选题]")){
			return LineType.MUTI_QUESTION;
		}else if(line.contains("[简答题]")){
			return LineType.TEXT_ENTRY;
		}else if(Pattern.compile("^[0-9]*、.*").matcher(line).matches()){
			return LineType.SINGLE_QUESTION;
		}
		else if("0123456789".contains(String.valueOf(line.charAt(0)))&&(temp == null || temp.getType().equals(SurveyQuestion.QuesType.TEXTENTRY))){
			return LineType.SINGLE_QUESTION;
		}
		else return LineType.CHOICE;
	}
	
	
	
	private class LineType{
		public static final int EMPTY_LINE = 0;
		public static final int SINGLE_QUESTION = 1;
		public static final int MUTI_QUESTION = 2;
		public static final int TEXT_ENTRY = 3;
		public static final int CHOICE = -1;
	}
	
	private List<ChoiceGroup> generateOneGroupList(){
		List<ChoiceGroup> choiceGroups = Lists.newArrayList();
		ChoiceGroup result = new ChoiceGroup();
		List<Choice> choices = Lists.newArrayList();
		result.setChoices(choices);
		choiceGroups.add(result);
		return choiceGroups;
	}
	

}
