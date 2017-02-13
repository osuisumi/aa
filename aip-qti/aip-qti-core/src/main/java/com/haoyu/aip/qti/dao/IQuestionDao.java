/**
 * 
 */
package com.haoyu.aip.qti.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.entity.Question;

/**
 * @author lianghuahuang
 *
 */
public interface IQuestionDao {
	
	Question selectQuestioById(String id);
	
	List<Question> selectQuestion(Map<String,Object> parameter);
	
	List<Question> selectQuestion(Map<String,Object> parameter,PageBounds pageBounds);
	
	int insertQuestion(Question question);
	
	int updateQuestion(Question question);
	
	int deleteQuestionByLogic(String id);
	
	int deleteQuestionByPhysics(String id);
}
