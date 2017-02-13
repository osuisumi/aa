/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.qti.dao.ITestDeliveryUserOutcomeDao;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestDeliveryUserOutcome;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestDeliveryUserOutcomeDao extends MybatisDao implements
		ITestDeliveryUserOutcomeDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryUserOutcomeDao#insertTestDeliveryUserOutcome(com.haoyu.aip.qti.entity.TestDeliveryUserOutcome)
	 */
	@Override
	public int insertTestDeliveryUserOutcome(
			TestDeliveryUserOutcome testDeliverUserOutcome) {
		testDeliverUserOutcome.setDefaultValue();
		return super.insert(testDeliverUserOutcome);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryUserOutcomeDao#deleteTestDleverUserOutcome(com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public int deleteTestDleverUserOutcome(TestDeliveryUser testDeliveryUser) {
		return super.delete("deleteByTestDeliveryUser",testDeliveryUser.getId());
	}

}
