/**
 * 
 */
package com.haoyu.aip.qti.dao;

import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestDeliveryUserOutcome;

/**
 * @author lianghuahuang
 *
 */
public interface ITestDeliveryUserOutcomeDao {
	
	int insertTestDeliveryUserOutcome(TestDeliveryUserOutcome testDeliverUserOutcome);
	
	int deleteTestDleverUserOutcome(TestDeliveryUser testDeliveryUser);
}
