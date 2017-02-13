/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.qti.dao.IQuestionPackageDao;
import com.haoyu.aip.qti.entity.QuestionPackage;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class QuestionPackageDao extends MybatisDao implements
		IQuestionPackageDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionPackageDao#selectQuestionPackageById(java.lang.String)
	 */
	@Override
	public QuestionPackage selectQuestionPackageById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionPackageDao#insertQuestionPackage(com.haoyu.aip.qti.entity.QuestionPackage)
	 */
	@Override
	public int insertQuestionPackage(QuestionPackage questionPackage) {
		questionPackage.setDefaultValue();
		return super.insert(questionPackage);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.IQuestionPackageDao#updateQuestionPackage(com.haoyu.aip.qti.entity.QuestionPackage)
	 */
	@Override
	public int updateQuestionPackage(QuestionPackage questionPackage) {
		questionPackage.setUpdateValue();
		return super.update(questionPackage);
	}

}
