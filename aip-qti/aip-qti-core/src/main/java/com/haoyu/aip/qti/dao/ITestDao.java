/**
 * 
 */
package com.haoyu.aip.qti.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.entity.Test;

/**
 * @author lianghuahuang
 *
 */
public interface ITestDao {
	
	Test selectTestById(String id);
	
	List<Test> selectTest(Map<String,Object> parameter);
	
	List<Test> selectTest(Map<String,Object> parameter,PageBounds pageBounds);
	
	int insertTest(Test test);
	
	int updateTest(Test test);
	
	int deleteTestByLogic(String id);
	
	int deleteTestByPhysics(String id);
}
