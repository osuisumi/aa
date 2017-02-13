/**
 * 
 */
package com.haoyu.aip.qti.dao;

import com.haoyu.aip.qti.entity.TestDelivery;

/**
 * @author lianghuahuang
 *
 */
public interface ITestDeliveryDao {
	
	TestDelivery selectTestDelivery(String testId,String relationId);
	
	int insertTestDelivery(TestDelivery testDelivery);
}
