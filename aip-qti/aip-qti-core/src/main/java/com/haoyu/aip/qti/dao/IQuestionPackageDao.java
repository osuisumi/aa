/**
 * 
 */
package com.haoyu.aip.qti.dao;

import com.haoyu.aip.qti.entity.QuestionPackage;

/**
 * @author lianghuahuang
 *
 */
public interface IQuestionPackageDao {
	QuestionPackage selectQuestionPackageById(String id);
	
	int insertQuestionPackage(QuestionPackage testPackage);
	
	
	int updateQuestionPackage(QuestionPackage testPackage);
}
