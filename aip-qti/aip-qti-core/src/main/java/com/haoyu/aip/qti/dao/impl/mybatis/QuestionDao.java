/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.dao.IQuestionDao;
import com.haoyu.aip.qti.entity.Question;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class QuestionDao extends MybatisDao implements IQuestionDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#selectQuestioById(java.lang.String)
	 */
	@Override
	public Question selectQuestioById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#selectQuestion(java.util.Map)
	 */
	@Override
	public List<Question> selectQuestion(Map<String, Object> parameter) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#selectQuestion(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Question> selectQuestion(Map<String, Object> parameter,
			PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#insertQuestion(com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public int insertQuestion(Question question) {
		question.setDefaultValue();		
		return super.insert(question);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#updateQuestion(com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public int updateQuestion(Question question) {
		question.setUpdateValue();
		return super.update(question);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#deleteQuestionByLogic(java.lang.String)
	 */
	@Override
	public int deleteQuestionByLogic(String id) {
		Question question = new Question(id);
		question.setUpdateValue();
		return super.deleteByLogic(question);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionDao#deleteQuestionByPhysics(java.lang.String)
	 */
	@Override
	public int deleteQuestionByPhysics(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
