/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.dao.ITestDao;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestDao extends MybatisDao implements ITestDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#selectTestById(java.lang.String)
	 */
	@Override
	public Test selectTestById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#selectTest(java.util.Map)
	 */
	@Override
	public List<Test> selectTest(Map<String, Object> parameter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#selectTest(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Test> selectTest(Map<String, Object> parameter,
			PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#insertTest(com.haoyu.aip.qti.entity.Test)
	 */
	@Override
	public int insertTest(Test test) {
		test.setDefaultValue();
		return super.insert(test);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#updateTest(com.haoyu.aip.qti.entity.Test)
	 */
	@Override
	public int updateTest(Test test) {
		test.setUpdateValue();
		return super.update(test);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#deleteTestByLogic(java.lang.String)
	 */
	@Override
	public int deleteTestByLogic(String id) {
		Test test = new Test(id);
		test.setUpdateValue();
		return super.deleteByLogic(test);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDao#deleteTestByPhysics(java.lang.String)
	 */
	@Override
	public int deleteTestByPhysics(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
