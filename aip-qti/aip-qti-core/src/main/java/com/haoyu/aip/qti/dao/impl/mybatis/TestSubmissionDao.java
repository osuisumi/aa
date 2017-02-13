/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.qti.dao.ITestSubmissionDao;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestSubmission;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestSubmissionDao extends MybatisDao implements ITestSubmissionDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestSubmissionDao#insertTestSubmission(com.haoyu.aip.qti.entity.TestSubmission)
	 */
	@Override
	public int insertTestSubmission(TestSubmission testSubmission) {
		testSubmission.setDefaultValue();
		return super.insert(testSubmission);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestSubmissionDao#deleteTestSubmissionByPhysics(com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public int deleteTestSubmissionByPhysics(TestDeliveryUser testDeliveryUser) {
		return super.delete("deleteByTestDeliveryUser", testDeliveryUser.getId());
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestSubmissionDao#deleteTestSubmissionByPhysics(java.lang.String)
	 */
	@Override
	public int deleteTestSubmissionByPhysics(String id) {
		return super.delete("deleteById",id);
	}

}
