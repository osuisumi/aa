/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.qti.dao.ITestPackageDao;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestPackageDao extends MybatisDao implements ITestPackageDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestPackageDao#selectTestPackageById(java.lang.String)
	 */
	@Override
	public TestPackage selectTestPackageById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestPackageDao#insertTestPackage(com.haoyu.aip.qti.entity.TestPackage)
	 */
	@Override
	public int insertTestPackage(TestPackage testPackage) {
		testPackage.setDefaultValue();
		return super.insert(testPackage);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestPackageDao#updateTestPackage(com.haoyu.aip.qti.entity.TestPackage)
	 */
	@Override
	public int updateTestPackage(TestPackage testPackage) {
		testPackage.setUpdateValue();
		return super.update(testPackage);
	}

}
