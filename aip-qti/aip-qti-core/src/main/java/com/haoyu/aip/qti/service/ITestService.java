/**
 * 
 */
package com.haoyu.aip.qti.service;

import java.util.List;

import com.haoyu.aip.qti.entity.Question;
import com.haoyu.aip.qti.entity.QuestionFormKey;
import com.haoyu.aip.qti.entity.QuestionPackage;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ITestService {
	
	Test findTestById(String id);
	
	Response createTest(Test test); 
	
	Response addTestQuestion(Test test,Question question,QuestionFormKey qfk);
	
	Response addTestQuestions(Test test,List<QuestionPackage> questionPackages);
	
	Response importTestQuestions(Test test,String questionsText,QuestionFormKey qfk);
	
	Response removeTestQuestion(Test test,QuestionFormKey questionFormKey);
	
	Response updateTest(Test test);

	List<Question> findQuestionsByTestId(String testId);
	
	Response updateTestQuestionSequence(Test test,QuestionFormKey targetQfk,QuestionFormKey sourceQfk);
}
