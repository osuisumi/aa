/**
 * 
 */
package com.haoyu.aip.qti.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.entity.Question;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IQuestionService {
	
	Question findQuestionById(String id);
	
	List<Question> findQuestions(Question question);
	
	List<Question> findQuestions(Question question,PageBounds pageBounds);
	
	Response createQuestion(Question question);
	
	Response createTestQuestion(TestPackage testPackage,Question question);
	
	Response updateQuestion(Question question);
	
	Response deleteQuestion(String id);
	
}
